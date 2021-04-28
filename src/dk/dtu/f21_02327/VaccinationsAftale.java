package dk.dtu.f21_02327;

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
    private final String vaccineType;
    private final String lokation;

    public VaccinationsAftale(long cprnr, String navn, Date vaccinationsDato, String vaccineType, String lokation) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaccinationsDato = vaccinationsDato;
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

    public String getVaccineType() {
        return vaccineType;
    }

    public String getLokation() {
        return lokation;
    }

    @Override
    public String toString() {
        final String D = ";";
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd" +D +"HHmm");

        return getCprnr() +D + getNavn() +D +dateFormatter.format(getVaccinationsDato()) +D +getLokation() +D +getVaccineType();
    }
}
