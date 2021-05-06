package dk.dtu.f21_02327.Model;

import java.time.LocalDate;

public class VaccineRapport
{

    String cpr;
    String personNavn;
    Vacciner vaccineType;
    Lokation lokation;
    LocalDate dato;
    int tid;
    int medarbejderID;
    String medarbejderNavn;


    public VaccineRapport(String cpr, String personNavn, Vacciner vaccineType,
                          int lokation, LocalDate dato, int tid,
                          int medarbejderID, String medarbejderNavn) {
        this.cpr = cpr;
        this.personNavn = personNavn;
        this.vaccineType = vaccineType;
        this.lokation = Lokation.reversePostal(lokation);
        this.dato = dato;
        this.tid = tid;
        this.medarbejderID = medarbejderID;
        this.medarbejderNavn = medarbejderNavn;
    }

    public String getCpr() {
        return cpr;
    }

    public String getPersonNavn() {
        return personNavn;
    }

    public Vacciner getVaccineType() {
        return vaccineType;
    }

    public Lokation getLokation() {
        return lokation;
    }

    public LocalDate getDato() {
        return dato;
    }

    public int getTid() {
        return tid;
    }

    public int getMedarbejderID() {
        return medarbejderID;
    }

    public String getMedarbejderNavn() {
        return medarbejderNavn;
    }


}
