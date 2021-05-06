package dk.dtu.f21_02327.Model;

import java.util.List;

public enum Lokation {
    hill("hill"), kbh("kbh"),
    aarhus("aarhus"), nakskov("nakskov"),
    odense("odense"), kolding("kolding");


    private String afdelingsNavn;
    private Lager lager;

    Lokation(String afdelingsNavn)
    {
        this.afdelingsNavn = afdelingsNavn;
    }


    public int getPostalCode()
    {

        switch (this)
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

    public static Lokation reversePostal(int postal)
    {
        switch (postal)
        {
            case 3400:
                return hill;
            case 1570:
                return kbh;
            case 8000:
                return aarhus;
            case 4900:
                return nakskov;
            case 5000:
                return odense;
            case 6000:
                return kolding;
            default:
                return kbh;
        }
    }

}
