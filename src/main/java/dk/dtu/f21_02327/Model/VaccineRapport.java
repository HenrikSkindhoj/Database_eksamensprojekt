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
        switch (lokation)
        {
            case 3400:
                this.lokation = Lokation.hill;
                break;
            case 1570:
                this.lokation = Lokation.kbh;
                break;
            case 8000:
                this.lokation = Lokation.aarhus;
                break;
            case 4900:
                this.lokation = Lokation.nakskov;
                break;
            case 5000:
                this.lokation = Lokation.odense;
                break;
            case 6000:
                this.lokation = Lokation.kolding;
                break;
            default:
                this.lokation = Lokation.kbh;
        }
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
