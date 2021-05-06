package dk.dtu.f21_02327.Model;

import java.time.LocalDate;

public class MaanedesRapport
{
    /*
    cpr
    vaccine
    dato
    vaccinepris
     */

    String cpr;
    Vacciner vaccine;
    LocalDate dato;
    int vaccinePris;

    public MaanedesRapport(String cpr, Vacciner vaccine, LocalDate dato, int vaccinePris) {
        this.cpr = cpr;
        this.vaccine = vaccine;
        this.dato = dato;
        this.vaccinePris = vaccinePris;
    }

    public String getCpr() {
        return cpr;
    }

    public Vacciner getVaccine() {
        return vaccine;
    }

    public LocalDate getDato() {
        return dato;
    }

    public int getVaccinePris() {
        return vaccinePris;
    }

}
