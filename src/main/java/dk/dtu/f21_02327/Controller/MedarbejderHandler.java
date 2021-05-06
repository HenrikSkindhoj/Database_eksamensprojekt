package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.LokationsMapper;
import dk.dtu.f21_02327.Model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class MedarbejderHandler {

    ArrayList<Medarbejder> medarbejdere;

    public MedarbejderHandler(LokationsMapper lokationsMapper) throws SQLException {
        medarbejdere = lokationsMapper.loadMedarbejdere();
        LinkedList<Certifikater> certifs = lokationsMapper.loadCertificates();

        for (Certifikater certifikater: certifs)
        {
            for(Medarbejder medarbejder: medarbejdere)
            {
                if(certifikater.getMedarbejderID() == medarbejder.getMedarbejderID())
                {
                    medarbejder.setCertifikat(certifikater);
                    break;
                }
            }
        }
    }

    public Medarbejder getAvailWorker(Lokation lokation, LocalDate date, int startTime, Vacciner certVac)
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
                        for(Certifikater cert : medarbejder.getCertifikat())
                        {
                            if(cert.getVaccinationsTypeID() == certVac)
                            {
                                return medarbejder;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


}
