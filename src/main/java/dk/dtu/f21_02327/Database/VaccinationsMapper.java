package dk.dtu.f21_02327.Database;

import dk.dtu.f21_02327.Model.VaccinationsAftale;

import java.sql.*;


public class VaccinationsMapper{

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

            ps.setInt(1,aftale.getPostalCode());
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

}
