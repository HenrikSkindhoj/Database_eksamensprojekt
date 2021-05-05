package dk.dtu.f21_02327.Model;

public class Person {

    private String cpr;
    private String personNavn;

    public Person(String cpr, String personNavn)
    {
        this.cpr = cpr;
        this.personNavn = personNavn;
    }

    public String getCpr() {
        return cpr;
    }

    public String getPersonNavn() {
        return personNavn;
    }
}
