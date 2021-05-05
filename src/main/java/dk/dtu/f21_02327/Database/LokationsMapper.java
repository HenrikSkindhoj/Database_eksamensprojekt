package dk.dtu.f21_02327.Database;

import dk.dtu.f21_02327.Model.Lokation;

import java.sql.*;


public class LokationsMapper extends DBMap {
    private DBConnection connector;

    public LokationsMapper(DBConnection connector) {
        this.connector = connector;
    }

    /*
    public boolean createLokaltionInDB(Lokation lokation){
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = getInsertLocationStatement();

            // To be removed! Next lines contain values only needed for this execution!
            Statement statement = connection.createStatement();
            statement.execute("SET foreign_key_checks = 0");

            ps.setInt(1,lokation.getMedarbejdere().ordinal);
            ps.setString(2,lokation.getAfdelingsNavn());
            ps.setInt(3, lokation.getLager());

            ps.executeUpdate();

            // To be removed! Same reason as above!
            statement = connection.createStatement();
            statement.execute("SET foreign_key_checks = 1");


            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Creating loaction in db failed! \n" +
                    "Check if database primitives are correct (int, String)");
        }
        return false;
    }

     */



    private static final String SQL_INSERT_LOCATION =
            "INSERT INTO Location(deptName, Employee_employeeID, Inventory) VALUES (?,?,?)";

    private PreparedStatement insert_location_stmt = null;

    private PreparedStatement getInsertLocationStatement()
    {
        if(insert_location_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                insert_location_stmt = connection.prepareStatement(
                        SQL_INSERT_LOCATION,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return insert_location_stmt;
    }


    @Override
    public boolean createToDB(Object e) {
        return false;
    }
}
