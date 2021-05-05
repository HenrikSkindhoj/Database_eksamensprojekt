package dk.dtu.f21_02327;

import dk.dtu.f21_02327.Controller.AppController;

import java.sql.SQLException;

public class RunApp {

    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.start();

        /*
        try {
            appController.createRepports();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }

         */


    }
}
