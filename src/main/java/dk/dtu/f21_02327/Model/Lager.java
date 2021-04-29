package dk.dtu.f21_02327.Model;

import java.util.*;

public class Lager {
    private String afdelingsNavn;
    private HashMap<Vacciner,Integer> vaccinationsTyper;

    public Lager(){
        vaccinationsTyper = new HashMap<>();
        for(Vacciner vac: Vacciner.values())
        {
            vaccinationsTyper.put(vac,0);
        }
    }

    public void setInventory(Vacciner vacineType, int inventory)
    {
        for( Map.Entry<Vacciner, Integer> entry : vaccinationsTyper.entrySet())
        {
            if(entry.getKey() == vacineType)
            {
                vaccinationsTyper.put(vacineType, inventory);
            }
        }
    }

}
