package dk.dtu.f21_02327.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Medarbejder {

    private int medarbejderID;
    private String navn;
    private int løn;
    private String jobTitel;
    private ArrayList<Vagt> vagtplan = new ArrayList<>();
    private ArrayList<Certifikater> certifikat = new ArrayList<>();

    public Medarbejder(int medarbejderID, String navn, int løn, String jobTitel)
    {
        this.medarbejderID = medarbejderID;
        this.navn = navn;
        this.løn = løn;
        this.jobTitel = jobTitel;
    }

    public void setVagt(Vagt vagt) {
        vagtplan.add(vagt);
    }
    public void setCertifikat(Certifikater certif)
    {
        certifikat.add(certif);
    }

    public ArrayList<Vagt> getVagtplan() {
        return vagtplan;
    }

    public int getMedarbejderID() {
        return medarbejderID;
    }

    public String getNavn() {
        return navn;
    }

    public int getLøn() {
        return løn;
    }

    public String getJobTitel() {
        return jobTitel;
    }

    public ArrayList<Certifikater> getCertifikat() {
        return certifikat;
    }
}
