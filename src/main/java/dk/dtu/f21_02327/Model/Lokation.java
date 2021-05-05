package dk.dtu.f21_02327.Model;

import java.util.List;

public enum Lokation {
    hill("hill"), kbh("kbh"), aarhus("aarhus"), nakskov("nakskov"),
    odense("odense"), kolding("kolding");


    private String afdelingsNavn;
    private List<Medarbejder> medarbejdere;
    private Lager lager;

    Lokation(String afdelingsNavn)
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
