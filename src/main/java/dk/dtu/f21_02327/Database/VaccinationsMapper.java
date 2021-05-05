package dk.dtu.f21_02327.Database;

import com.opencsv.CSVWriter;
import dk.dtu.f21_02327.Model.VaccinationsAftale;
import dk.dtu.f21_02327.Model.VaccineRapport;
import dk.dtu.f21_02327.Model.Vacciner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class VaccinationsMapper{

    private DBConnection connector;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public VaccinationsMapper(DBConnection connector) {
        this.connector = connector;
    }

    public boolean createAftaleInDB(VaccinationsAftale aftale)
    {
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = getInsertAftaleStatement();

            ps.setInt(1,aftale.getLokation().getPostalCode());
            ps.setInt(2,aftale.getVaccineType().ordinal());
            ps.setInt(3,9);
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

    public void createReport(ArrayList<VaccineRapport> rapports)
    {
        File file = new File("src/main/resources/Reports/Report-" +
                formatter.format(rapports.get(0).getDato()) +".csv");
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
                        " and medarbejder.medarbejderID = VaccinationsAftale.medarbejderID;";
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

}
