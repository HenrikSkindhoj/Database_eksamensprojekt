package dk.dtu.f21_02327.Model;

import java.time.LocalDate;

public class Certifikater {

    private int medarbejderID;
    private LocalDate certifikatDato;
    private Vacciner vaccinationsTypeID;

    public Certifikater(int medarbejderID, LocalDate certifikatDato, Vacciner vaccinationsTypeID)
    {
        this.medarbejderID = medarbejderID;
        this.certifikatDato = certifikatDato;
        this.vaccinationsTypeID = vaccinationsTypeID;
    }

    public int getMedarbejderID() {
        return medarbejderID;
    }

    public LocalDate getCertifikatDato() {
        return certifikatDato;
    }

    public Vacciner getVaccinationsTypeID() {
        return vaccinationsTypeID;
    }

}
