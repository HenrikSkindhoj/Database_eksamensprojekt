package dk.dtu.f21_02327;

import dk.dtu.f21_02327.Database.DBConnection;

import java.sql.*;

public class RunApp {

    public static void main(String[] args) {
        try{

            DBConnection db = new DBConnection();
            Connection connection = db.getConnection();
            System.out.println(connection.isClosed());

        } catch(SQLException e){
            System.out.println(e);
        }





        /*
        DBConnection connection = new DBConnection();
        System.out.println(connection.getConnection().isClosed());
         */

    }
}
