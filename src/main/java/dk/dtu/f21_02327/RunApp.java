package dk.dtu.f21_02327;

import dk.dtu.f21_02327.Controller.AppController;
import dk.dtu.f21_02327.Database.DBConnection;
import dk.dtu.f21_02327.Database.DBMap;
import dk.dtu.f21_02327.Database.PersonMapper;
import dk.dtu.f21_02327.Model.Person;
import dk.dtu.f21_02327.Model.Vacciner;

import java.sql.*;

public class RunApp {

    public static void main(String[] args) {
        AppController appController = new AppController();
        appController.start();

    }
}
