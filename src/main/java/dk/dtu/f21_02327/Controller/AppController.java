package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.*;
import dk.dtu.f21_02327.Model.Person;
import dk.dtu.f21_02327.Model.VaccinationsAftale;
import dk.dtu.f21_02327.Model.VaccineRapport;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppController {

    public void start() {
        IndlaesVaccinationsAftaler indlaes = new IndlaesVaccinationsAftaler();
        DBConnection connection = new DBConnection();
        VaccinationsMapper vaccinationsMapper = new VaccinationsMapper(connection);
        PersonMapper personMapper = new PersonMapper(connection);
        List<VaccinationsAftale> aftaler = null;
        System.out.println("Hvad vil du foretage dig?");
        System.out.println("For at indlæse vaccinations aftaler, tast \"LD\"");
        System.out.println("For at printe en daglig rapport, tast \"PD\"");
        System.out.println("For at printe en månedlig rapport, tast \"PM\"");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        switch (command){
            case "LD":
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
                break;

            case "PD":

                System.out.println("Skriv dato (yyyy-MM-dd): ");
                String dato = sc.nextLine();
                try {
                    createRepports(dato);
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
                break;

            case "PM":
                System.out.println("TODO");

            default:
                System.out.println("nice meme.");
        }

        }



    public void createRepports(String dato) throws SQLException {
        DBConnection connection = new DBConnection();
        VaccinationsMapper vaccinationsMapper = new VaccinationsMapper(connection);

        ArrayList<VaccineRapport> listOfRapports = vaccinationsMapper.LoadReportValues(dato);
        vaccinationsMapper.createReport(listOfRapports);

    }
}
