package dk.dtu.f21_02327.Model;

import dk.dtu.f21_02327.Model.Lokation;
import dk.dtu.f21_02327.Model.Vacciner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private final String cprnr;
    private final String navn;
    private int medarbejderID;
    private final LocalDate vaccinationsDato;
    private int vaccinationsTidspunkt;
    private final Vacciner vaccineType;
    private final Lokation lokation;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public VaccinationsAftale(String cprnr, String navn, Date vaccinationsDato, int vaccinationsTidspunkt,
                              Vacciner vaccineType, Lokation lokation) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaccinationsDato = vaccinationsDato.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.vaccinationsTidspunkt = vaccinationsTidspunkt;
        this.vaccineType = vaccineType;
        this.lokation = lokation;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public java.sql.Date getVaccinationsDato() {
        vaccinationsDato.format(formatter);

        return java.sql.Date.valueOf(vaccinationsDato);
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

    @Override
    public String toString() {
        final String D = ";";
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd" +D +"HHmm");

        return getCprnr() +D + getNavn() +D +dateFormatter.format(getVaccinationsDato()) +D +getLokation() +D +getVaccineType();
    }
}
