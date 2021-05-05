package dk.dtu.f21_02327.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Medarbejder {

    private int medarbejderID;
    private String navn;
    private int løn;
    private String jobTitel;
    private ArrayList<Vagt> vagtplan = new ArrayList<>();

    public Medarbejder(int medarbejderID, String navn, int løn, String jobTitel)
    {
        this.medarbejderID = medarbejderID;
        this.navn = navn;
        this.løn = løn;
        this.jobTitel = jobTitel;
    }

    public void setVagt(LocalDate localDate, int startTime, int slutTime, Lokation lokation) {
        Vagt vagt = new Vagt(localDate,startTime,lokation,this);
        vagtplan.add(vagt);
    }

    public ArrayList<Vagt> getVagtplan() {
        return vagtplan;
    }
}
