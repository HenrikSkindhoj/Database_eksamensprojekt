package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.*;
import dk.dtu.f21_02327.Model.Person;
import dk.dtu.f21_02327.Model.VaccinationsAftale;
import dk.dtu.f21_02327.Model.VaccineRapport;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    public void start() {
        IndlaesVaccinationsAftaler indlaes = new IndlaesVaccinationsAftaler();
        DBConnection connection = new DBConnection();
        VaccinationsMapper vaccinationsMapper = new VaccinationsMapper(connection);
        PersonMapper personMapper = new PersonMapper(connection);
        List<VaccinationsAftale> aftaler = null;

        try {

            aftaler = indlaes.indlaesAftaler("src/main/resources/docs/vaccinationsaftaler.csv");
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for (VaccinationsAftale aftale: aftaler) {

            Person newPerson = new Person(aftale.getCprnr(),aftale.getNavn());
            personMapper.createPersonInDB(newPerson);

        }

        for (VaccinationsAftale aftale : aftaler) {
            vaccinationsMapper.createAftaleInDB(aftale);
        }
        try{
            connection.getConnection().close();
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("Error closing the connection to Database");
        }

    }

    public void createRepports() throws SQLException {
        DBConnection connection = new DBConnection();
        VaccinationsMapper vaccinationsMapper = new VaccinationsMapper(connection);

        ArrayList<VaccineRapport> listOfRapports = vaccinationsMapper.LoadReportValues("2021-04-11");
        vaccinationsMapper.createReport(listOfRapports);

    }
}
