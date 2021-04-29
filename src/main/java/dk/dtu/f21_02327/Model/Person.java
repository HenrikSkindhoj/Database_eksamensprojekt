package dk.dtu.f21_02327.Model;

public class Person {

    private long cpr;
    private String personNavn;
    Vacciner vaccine;

    public Person(int cpr, String personNavn, Vacciner vacciner)
    {
        this.cpr = cpr;
        this.personNavn = personNavn;
        vaccine = vacciner;
    }

    public long getCpr() {
        return cpr;
    }

    public String getPersonNavn() {
        return personNavn;
    }

    public Vacciner getVaccine() {
        return vaccine;
    }
}
