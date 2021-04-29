package dk.dtu.f21_02327.Model;

public class Medarbejder {

    private int medarbejderID;
    private String navn;
    private int løn;
    private String jobTitel;

    public Medarbejder(int medarbejderID, String navn, int løn, String jobTitel)
    {
        this.medarbejderID = medarbejderID;
        this.navn = navn;
        this.løn = løn;
        this.jobTitel = jobTitel;
    }
}
