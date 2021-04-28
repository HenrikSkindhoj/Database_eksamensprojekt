package dk.dtu.f21_02327.Database;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import dk.dtu.f21_02327.Controller.VaccinationsAftale;
import dk.dtu.f21_02327.Database.IndlaesVaccinationsAftaler;

public class IndlaesAftalerEksempel {

    public static void main(String[] args) {

        System.out.println("Write the name of the file you want to load into the Database");
        Scanner sc = new Scanner(System.in);


        IndlaesVaccinationsAftaler laeser = new IndlaesVaccinationsAftaler();
        try {
            List<VaccinationsAftale> aftaler = laeser.indlaesAftaler(sc.nextLine());
            for(VaccinationsAftale aftale : aftaler) {
                System.out.println(aftale);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}