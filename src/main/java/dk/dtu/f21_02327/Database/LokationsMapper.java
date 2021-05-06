package dk.dtu.f21_02327.Database;

import dk.dtu.f21_02327.Model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


public class LokationsMapper{
    private DBConnection connector;

    public LokationsMapper(DBConnection connector) {
        this.connector = connector;
    }

    private PreparedStatement select_medarbejdereFromDate_asc_stmt = null;

    private PreparedStatement getSelectMedarbejdereASCStatement()
    {
        //TODO få sql query af henrik
        String SQL_SELECT_MEDARBEJDERE_ASC = "select Vagt.vagtID, Vagt.medarbejderID, Vagt.startTidspunkt," +
                " Vagt.slutTidspunkt, Vagt.dato," +
                " Medarbejder.MedarbejderID, Medarbejder.navn," +
                " Medarbejder.løn, Medarbejder.jobTitle, VagtPlan.postNR " +
                "from Vagt " +
                "inner join Medarbejder " +
                "on Vagt.MedarbejderID = Medarbejder.MedarbejderID " +
                "inner join VagtPlan " +
                "on VagtPlan.vagtID = vagt.vagtID";

        if(select_medarbejdereFromDate_asc_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                select_medarbejdereFromDate_asc_stmt = connection.prepareStatement(
                        SQL_SELECT_MEDARBEJDERE_ASC);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return select_medarbejdereFromDate_asc_stmt;
    }

    public ArrayList<Medarbejder> loadMedarbejdere() throws SQLException {
        ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
        PreparedStatement ps = getSelectMedarbejdereASCStatement();
        ResultSet rs = ps.executeQuery();
        boolean boo = false;

        while(rs.next())
        {
            int medarbejderID = rs.getInt("medarbejderID");
            String navn = rs.getString("navn");
            int løn = rs.getInt("løn");
            String jobTitle = rs.getString("jobTitle");

            Medarbejder medarbejder = new Medarbejder(medarbejderID,navn,løn,jobTitle);

            for (Medarbejder medarbej: medarbejdere)
            {
                if(medarbejder.getMedarbejderID() == medarbej.getMedarbejderID())
                {
                    boo = true;
                    medarbejder = medarbej;
                    break;
                }
            }

            if(!boo)
            {
                medarbejdere.add(medarbejder);

            }
            boo = false;

            LocalDate shiftDate = rs.getDate("Vagt.dato").toLocalDate();
            int startTime = rs.getInt("Vagt.startTidspunkt");
            int slutTime = rs.getInt("Vagt.slutTidspunkt");
            int postnr = rs.getInt("Vagtplan.postNr");


            Vagt vagt = new Vagt(shiftDate,startTime, slutTime, Lokation.reversePostal(postnr),medarbejder);
            medarbejder.setVagt(vagt);

        }
        return medarbejdere;
    }

    public LinkedList<Certifikater> loadCertificates() throws SQLException {
        LinkedList<Certifikater> certifikaterArrayList = new LinkedList<>();
        PreparedStatement ps = getSelectCertifcateASCStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int medarbejderID = rs.getInt("medarbejderID");
            LocalDate date = rs.getDate("certifikatDato").toLocalDate();
            Vacciner vaccineType = Vacciner.values()[rs.getInt("vaccineTypeID")];

            certifikaterArrayList.add(new Certifikater(medarbejderID,date,vaccineType));
        }

        return certifikaterArrayList;
    }
    private PreparedStatement select_certifcate_asc_stmt = null;

    private PreparedStatement getSelectCertifcateASCStatement()
    {
        //TODO få sql query af henrik
        String SQL_SELECT_Certificates_ASC = "SELECT * FROM Certifikat";

        if(select_certifcate_asc_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                select_certifcate_asc_stmt = connection.prepareStatement(
                        SQL_SELECT_Certificates_ASC);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return select_certifcate_asc_stmt;
    }
}
