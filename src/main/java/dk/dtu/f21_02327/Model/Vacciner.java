package dk.dtu.f21_02327.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Vacciner {

    COVAXX("Covaxx"),
    DIVOC("Divoc"),
    BLAST3000("Blast3000"),
    ASPERA("Aspera");

    final public String displayName;

    final private List<Vacciner> options;


    Vacciner(String displayName, Vacciner... options)
    {
        this.displayName = displayName;
        this.options = Collections.unmodifiableList(Arrays.asList(options));
    }

    public List<Vacciner> getOptions()
    {
        return  options;
    }
}
