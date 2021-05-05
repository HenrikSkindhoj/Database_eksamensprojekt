package dk.dtu.f21_02327.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Vacciner {

    covaxx("covaxx"),
    divoc("divoc"),
    blast3000("blast3000"),
    aspera("aspera");

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
