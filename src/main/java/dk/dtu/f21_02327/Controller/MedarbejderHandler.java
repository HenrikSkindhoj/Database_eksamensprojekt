package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Database.LokationsMapper;
import dk.dtu.f21_02327.Model.*;

import java.sql.SQLException;
import java.sql.SQLOutput;
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

    public Medarbejder getAvailWorker(Lokation lokation, LocalDate date, Vacciner certVac)
    {

        for(Medarbejder medarbejder: medarbejdere)
        {
            for (Vagt vagt: medarbejder.getVagtplan())
            {
                if(vagt.getLokation() == lokation)
                {
                    if(vagt.getDate().isEqual(date))
                    {
                        for(Certifikater cert : medarbejder.getCertifikat())
                        {
                            if(cert.getVaccine() == certVac)
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
