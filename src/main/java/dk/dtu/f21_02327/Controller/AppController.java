package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.IndlaesAftalerEksempel;
import dk.dtu.f21_02327.Database.IndlaesVaccinationsAftaler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    public void start() {
        IndlaesVaccinationsAftaler indlaes = new IndlaesVaccinationsAftaler();
        List<VaccinationsAftale> aftaler = null;

        try {

            aftaler = indlaes.indlaesAftaler("src/main/resources/docs/vaccinationsaftaler.csv");
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        for( VaccinationsAftale aftale : aftaler)
        {
            System.out.println(aftale.getVaccinationsTidspunkt());
        }

    }
}
