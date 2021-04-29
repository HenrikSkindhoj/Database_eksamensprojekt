package dk.dtu.f21_02327.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum JobTitle
{
    DOCTOR("Læge"),
    NURSE("Sygeplejske"),
    VOLUNTARY("Frivilig");

    final public String displayTitle;

    final private List<JobTitle> options;

    JobTitle(String displayTitle, JobTitle... options)
    {
        this.displayTitle = displayTitle;
        this.options = Collections.unmodifiableList(Arrays.asList(options));

    }

    public List<JobTitle> getOptions()
    {
        return options;
    }
}
