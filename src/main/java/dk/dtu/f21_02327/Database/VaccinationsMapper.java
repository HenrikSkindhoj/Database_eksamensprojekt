package dk.dtu.f21_02327.Database;

import dk.dtu.f21_02327.Controller.VaccinationsAftale;
import dk.dtu.f21_02327.Model.Lokation;
import dk.dtu.f21_02327.Model.Vacciner;

import java.sql.*;
import java.time.LocalDate;

public class VaccinationsMapper extends DBMap{

    private DBConnection connector;

    public VaccinationsMapper(DBConnection connector) {
        this.connector = connector;
    }

    public boolean createAftaleInDB(VaccinationsAftale aftale)
    {
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = getInsertAftaleStatement();

            ps.setInt(2,aftale.getPostalCode());
            ps.setInt(3,aftale.getVaccineType().ordinal());
            ps.setInt(4,9);
            ps.setInt(5, (int) aftale.getCprnr());
            ps.setInt(6, aftale.getVaccinationsTidspunkt());
            ps.setDate(7, (Date) aftale.getVaccinationsDato());

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

    @Override
    public boolean createToDB(Object e) {
        return false;
    }
}
