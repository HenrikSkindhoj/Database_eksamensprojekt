package dk.dtu.f21_02327.Model;

import java.time.LocalDate;

public class Certifikater {

    private int medarbejderID;
    private LocalDate certifikatDato;
    private Vacciner vaccine;

    public Certifikater(int medarbejderID, LocalDate certifikatDato, Vacciner vaccine)
    {
        this.medarbejderID = medarbejderID;
        this.certifikatDato = certifikatDato;
        this.vaccine = vaccine;
    }

    public int getMedarbejderID() {
        return medarbejderID;
    }

    public LocalDate getCertifikatDato() {
        return certifikatDato;
    }

    public Vacciner getVaccine() {
        return vaccine;
    }

}
