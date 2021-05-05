package dk.dtu.f21_02327.Controller;

import dk.dtu.f21_02327.Model.Lokation;
import dk.dtu.f21_02327.Model.Vacciner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Denne klasse repræsenterer en vaccinationsaftale i den fil der modtages fra sundhedsmyndighederne dagligt.
 *
 * Klassen er en del af projektopgaven på Kursus 02327 F21
 *
 * @author Thorbjørn Konstantinovitz
 *
 */
public class VaccinationsAftale {
    private final long cprnr;
    private final String navn;
    private int medarbejderID;
    private final Date vaccinationsDato;
    private int vaccinationsTidspunkt;
    private Vacciner vaccineType;
    private Lokation lokation;

    public VaccinationsAftale(long cprnr, String navn, Date vaccinationsDato, int vaccinationsTidspunkt,
                              Vacciner vaccineType, Lokation lokation) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaccinationsDato = vaccinationsDato;
        this.vaccinationsTidspunkt = vaccinationsTidspunkt;

        this.vaccineType = vaccineType;
        this.lokation = lokation;
    }

    public long getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public Date getVaccinationsDato() {
        return vaccinationsDato;
    }

    public Vacciner getVaccineType() {
        return vaccineType;
    }

    public Lokation getLokation() {
        return lokation;
    }

    public int getVaccinationsTidspunkt() {
        return vaccinationsTidspunkt;
    }

    public int getPostalCode()
    {

        switch (this.lokation)
        {
            case hill:
                return 3400;
            case kbh:
                return 1570;
            case aarhus:
                return 8000;
            case nakskov:
                return 4900;
            case odense:
                return 5000;
            case kolding:
                return 6000;
            default:
                return 1570;
        }
    }

    @Override
    public String toString() {
        final String D = ";";
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd" +D +"HHmm");

        return getCprnr() +D + getNavn() +D +dateFormatter.format(getVaccinationsDato()) +D +getLokation() +D +getVaccineType();
    }
}
