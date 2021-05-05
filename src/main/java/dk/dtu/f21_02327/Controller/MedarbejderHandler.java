package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Model.Lokation;
import dk.dtu.f21_02327.Model.Medarbejder;
import dk.dtu.f21_02327.Model.Vagt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MedarbejderHandler {

    ArrayList<Medarbejder> medarbejdere;

    MedarbejderHandler(ArrayList<Medarbejder> medarbejdere)
    {
        this.medarbejdere = medarbejdere;
    }

    public Medarbejder getAvailWorker(Lokation lokation, LocalDate date, int startTime)
    {
        LocalDate dateTime = date;
        int startMin = startTime % 100;
        int startHour = (startTime - startMin)/100;
        LocalDateTime appointmentStart = dateTime.atTime(startHour,startMin);
        LocalDateTime appointmentEnd = dateTime.atTime(startHour,startMin+10);

        for(Medarbejder medarbejder: medarbejdere)
        {
            for (Vagt vagt: medarbejder.getVagtplan())
            {
                if(vagt.getLokation() == lokation)
                {
                    LocalDateTime medarbejderVagtTime = vagt.getLocalDateTime();
                    if(medarbejderVagtTime.isBefore(appointmentStart) || medarbejderVagtTime.isAfter(appointmentEnd))
                    {
                        return medarbejder;
                    }
                }
            }
        }
        return null;
    }


}
