package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.DBConnection;
import dk.dtu.f21_02327.Database.IndlaesAftalerEksempel;
import dk.dtu.f21_02327.Database.IndlaesVaccinationsAftaler;
import dk.dtu.f21_02327.Database.VaccinationsMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    public void start() {
        IndlaesVaccinationsAftaler indlaes = new IndlaesVaccinationsAftaler();
        VaccinationsMapper vaccinationsMapper = new VaccinationsMapper(new DBConnection());
        List<VaccinationsAftale> aftaler = null;

        try {

            aftaler = indlaes.indlaesAftaler("src/main/resources/docs/vaccinationsaftaler.csv");
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for( VaccinationsAftale aftale : aftaler)
        {
            System.out.println(aftale.getVaccineType());
        }

        vaccinationsMapper.createAftaleInDB(aftaler.get(0));
    }
}
