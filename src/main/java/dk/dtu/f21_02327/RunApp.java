package dk.dtu.f21_02327;

import dk.dtu.f21_02327.Database.DBConnection;
import dk.dtu.f21_02327.Database.DBMap;
import dk.dtu.f21_02327.Database.PersonMapper;
import dk.dtu.f21_02327.Model.Person;
import dk.dtu.f21_02327.Model.Vacciner;

import java.sql.*;

public class RunApp {

    public static void main(String[] args) {

        Person personToDB = new Person(1104981123,"Hans Christian Leth", Vacciner.COVAXX);

        PersonMapper someMap = new PersonMapper(new DBConnection());
        someMap.createPersonInDB(personToDB);

    }
}
