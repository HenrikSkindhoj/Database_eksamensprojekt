package dk.dtu.f21_02327.Database;

import com.opencsv.CSVWriter;
import dk.dtu.f21_02327.Controller.MedarbejderHandler;
import dk.dtu.f21_02327.Model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class VaccinationsMapper{

    private DBConnection connector;
    private LokationsMapper lokationsMapper;
    private MedarbejderHandler medarbejderHandler;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public VaccinationsMapper(DBConnection connector) {
        this.connector = connector;
        this.lokationsMapper = new LokationsMapper(connector);
        try {
            this.medarbejderHandler = new MedarbejderHandler(lokationsMapper);
        } catch (SQLException e)
        {
            System.err.println("Wow an error occurred in loading values");
            e.printStackTrace();
        }
    }

    public boolean createAftaleInDB(VaccinationsAftale aftale)
    {
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = getInsertAftaleStatement();

            Medarbejder worker = medarbejderHandler.getAvailWorker(aftale.getLokation(),
                    aftale.getLocalDate(),aftale.getVaccineType());
            ps.setInt(1,aftale.getLokation().getPostalCode());
            ps.setInt(2,aftale.getVaccineType().ordinal());
            if(worker != null)
                ps.setInt(3,worker.getMedarbejderID());
            else {
                System.err.println("Ingen arbejdstimer er lagt for denne dato");
                ps.setInt(3,1);
            }
            ps.setString(4, aftale.getCprnr());
            ps.setInt(5, aftale.getVaccinationsTidspunkt());
            ps.setDate(6, aftale.getVaccinationsDato());

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Creating aftale in db failed!");
        }
        return false;
    }

    private static final String SQL_INSERT_AFTALE =
            "INSERT INTO VaccinationsAftale(postNR, vaccineTypeID, " +
                    "medarbejderID, cpr, tidspunkt, dato) VALUES (?,?,?,?,?,?)";

    private PreparedStatement insert_aftale_stmt = null;

    private PreparedStatement getInsertAftaleStatement()
    {
        if(insert_aftale_stmt == null)
        {
            Connection connection = connector.getConnection();
            try{
                insert_aftale_stmt = connection.prepareStatement(
                        SQL_INSERT_AFTALE,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return insert_aftale_stmt;
    }

    public void createReport(String date)
    {
        ArrayList<VaccineRapport> rapports = null;
        try {
            rapports = LoadReportValues(date);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        File file = new File("src/main/resources/Reports/Report-" +
                date +".csv");
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"CPR","personNavn","VaccineType","Lokation",
                    "Dato","Tid","MedarbejderID","MedarbejderNavn"};
            writer.writeNext(header);

            for(VaccineRapport vaccineRapport: rapports)
            {
                String[] data = {vaccineRapport.getCpr(),vaccineRapport.getPersonNavn(),
                        vaccineRapport.getVaccineType().displayName,
                        Integer.toString(vaccineRapport.getLokation().getPostalCode()),
                        vaccineRapport.getDato().toString(),
                        Integer.toString(vaccineRapport.getTid()),
                        Integer.toString(vaccineRapport.getMedarbejderID()),
                        vaccineRapport.getMedarbejderNavn()
                };
                writer.writeNext(data);
            }
            writer.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createMontlyReport(String startDate, String endDate)
    {
        ArrayList<MaanedesRapport> reports = null;
        try {
            reports = loadMonthlyReportValues(startDate,endDate);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        File file = new File("src/main/resources/Reports/Report-" +
                startDate +"_to_"+ endDate +".csv");
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"CPR","Vaccine","Dato","VaccinePris"};
            writer.writeNext(header);

            for(MaanedesRapport report: reports)
            {
                String[] data = {
                        report.getCpr(),
                        report.getVaccine().displayName,
                        report.getDato().toString(),
                        Integer.toString(report.getVaccinePris())
                };
                writer.writeNext(data);
            }

            String[] footer = {" ", " ", "TOTAL PRICE ->", "=SUM(D2:D"+ reports.size() + ")"};
            writer.writeNext(footer);
            writer.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private PreparedStatement select_rapportvalues_asc_stmt = null;

    private PreparedStatement getSelectReportASCStatement(String date)
    {
        String SQL_SELECT_REPORTVALUES_ASC =
                "select VaccinationsAftale.postNR, VaccinationsAftale.vaccineTypeID," +
                        " VaccinationsAftale.medarbejderID, VaccinationsAftale.cpr," +
                        " person.personNavn, VaccinationsAftale.dato," +
                        " VaccinationsAftale.tidspunkt, medarbejder.navn " +
                        "from VaccinationsAftale " +
                        "natural join person " +
                        "join medarbejder " +
                        "where VaccinationsAftale.dato = '"+date+"' and VaccinationsAftale.cpr = person.cpr" +
                        " and medarbejder.medarbejderID = VaccinationsAftale.medarbejderID";
        if(select_rapportvalues_asc_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                select_rapportvalues_asc_stmt = connection.prepareStatement(
                        SQL_SELECT_REPORTVALUES_ASC);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return select_rapportvalues_asc_stmt;
    }

    public ArrayList<VaccineRapport> LoadReportValues(String date) throws SQLException {

        ArrayList<VaccineRapport> reporter = new ArrayList<>();
        PreparedStatement ps = getSelectReportASCStatement(date);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            String cpr = rs.getString("VaccinationsAftale.cpr");
            String personNavn = rs.getString("person.personNavn");
            int vaccineID = rs.getInt("VaccinationsAftale.vaccineTypeID");
            int postNr = rs.getInt("VaccinationsAftale.postNR");
            Date vacDate = rs.getDate("VaccinationsAftale.dato");
            int tidspunkt = rs.getInt("VaccinationsAftale.tidspunkt");
            int medarbejderID = rs.getInt("VaccinationsAftale.medarbejderID");
            String medarbejderNavn = rs.getString("medarbejder.navn");
            reporter.add(new VaccineRapport(cpr,personNavn, Vacciner.values()[vaccineID], postNr,
                    vacDate.toLocalDate(), tidspunkt,medarbejderID,medarbejderNavn));
        }

        return reporter;
    }

    private PreparedStatement selectMonthlyValues = null;

    private PreparedStatement getSelectMonthlyASCStatement(String startDate, String endDate)
    {
        String SQL_MONTHLYREPORT_STATEMENT_ASC =
                "select vaccinationsAftale.cpr, vaccinationsAftale.vaccineTypeID, vaccinationsAftale.dato, Vaccine.vaccinePris " +
                "from vaccinationsAftale " +
                "inner join Vaccine " +
                "where Vaccine.vaccineTypeID = vaccinationsAftale.vaccineTypeID " +
                "and (vaccinationsAftale.dato between '"+startDate+"' AND '"+endDate+"')";

        if(selectMonthlyValues == null)
        {
            Connection connection = connector.getConnection();
            try
            {
                selectMonthlyValues = connection.prepareStatement(SQL_MONTHLYREPORT_STATEMENT_ASC);
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return selectMonthlyValues;
    }

    public ArrayList<MaanedesRapport> loadMonthlyReportValues(String startDate, String endDate) throws SQLException {
        ArrayList<MaanedesRapport> reporter = new ArrayList<>();
        PreparedStatement ps = getSelectMonthlyASCStatement(startDate, endDate);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            String cpr = rs.getString("vaccinationsAftale.cpr");
            Vacciner vacc = Vacciner.values()[rs.getInt("vaccinationsAftale.vaccineTypeID")];
            LocalDate dato = rs.getDate("vaccinationsAftale.dato").toLocalDate();
            int vaccinePris = rs.getInt("Vaccine.vaccinePris");

            reporter.add(new MaanedesRapport(cpr,vacc,dato,vaccinePris));
        }

        return reporter;
    }

}
