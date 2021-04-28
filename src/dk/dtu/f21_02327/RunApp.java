package dk.dtu.f21_02327;

import dk.dtu.f21_02327.Database.DBConnection;

import java.sql.*;

public class RunApp {

    public static void main(String[] args) throws SQLException {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coronavacination");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from employee");
            System.out.println(con.isClosed());
            while (rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            con.close();
        } catch(Exception e){

        }





        /*
        DBConnection connection = new DBConnection();
        System.out.println(connection.getConnection().isClosed());
         */

    }
}
