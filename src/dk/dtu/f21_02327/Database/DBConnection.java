package dk.dtu.f21_02327.Database;

import java.sql.*;

public class DBConnection {


    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "coronavacination";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    Connection connection;


    public DBConnection() {
        try {
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
           /*
            Statement statement = connection.createStatement();
            String sql = "select * from coronavacination.employee";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
                System.out.println(rs.getString("name"));

            */

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public Connection getConnection() {
        return connection;
    }

}
