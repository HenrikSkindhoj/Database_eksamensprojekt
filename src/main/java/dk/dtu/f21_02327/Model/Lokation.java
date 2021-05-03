package dk.dtu.f21_02327.Model;

import java.util.List;

public class Lokation {
    private String afdelingsNavn;
    private List<Medarbejder> medarbejdere;
    private Lager lager;

    public Lokation(String afdelingsNavn)
    {
        this.afdelingsNavn = afdelingsNavn;
    }

    public void setLager(Lager lager)
    {
        this.lager = lager;
    }

    public Lager getLager()
    {
        return lager;
    }

    public void setMedarbejderID(List<Medarbejder> medarbejdere)
    {
        this.medarbejdere = medarbejdere;
    }

    public List<Medarbejder> getMedarbejdere()
    {
        return medarbejdere;
    }

    public String getAfdelingsNavn() {
        return afdelingsNavn;
    }
}
