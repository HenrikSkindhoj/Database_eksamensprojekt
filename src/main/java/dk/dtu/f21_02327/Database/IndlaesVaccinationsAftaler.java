package dk.dtu.f21_02327.Database;

/**
 * Denne klasse håndterer indlæsning af vaccinationsaftaler fra den fil der modtages fra sundhedsmyndighederne dagligt.
 * Klassen implementerer en simpel
 *
 *  Klassen er en del af projektopgaven på Kursus F21 02327 F21
 *
 * @author Thorbjørn Konstantinovitz
 *
 */

import dk.dtu.f21_02327.Model.VaccinationsAftale;
import dk.dtu.f21_02327.Model.Lokation;
import dk.dtu.f21_02327.Model.Vacciner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IndlaesVaccinationsAftaler {

    public static final String SEMICOLON_DELIMITER = ";";
    public static final String COMMA_DELIMITER = ",";
    private static final int NUMBER_OF_FIELDS_EXPECTED = 6;
    private final String delimiter = SEMICOLON_DELIMITER;
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");

    /**
     * Denne metode indlæser en fil med vaccinationsaftaler og returnerer en liste med VaccinationsAfale objekter der repræsenterer indholdet i filen.
     * @param filename filnavn på den fil der skal indlæses (inkl. sti hvis nødvendigt)
     * @return List indeholdende VaccinationsAftaler
     * @throws FileNotFoundException
     */
    public List<VaccinationsAftale> indlaesAftaler(String filename) throws IOException {
        List<VaccinationsAftale> aftaler = new ArrayList<>();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));

            String line;
            int lineNbr = 0;
            while ((line = in.readLine()) != null) {
                lineNbr++;
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(line)) {
                    rowScanner.useDelimiter(delimiter);
                    while (rowScanner.hasNext()) {
                        values.add(rowScanner.next());
                    }
                    switch(values.size()) {
                        case 0: // Ignore empty lines
                            break;
                        case NUMBER_OF_FIELDS_EXPECTED: // Parse values into VaccinationsAftale

                            String cprnr = null;
                            cprnr = values.get(0);
                            String navn = values.get(1);
                            String dato = values.get(2);
                            String tid = values.get(3);

                            // Pad to 4 digits.
                            // ----------------
                            for(int i = 4 - tid.length(); i > 0 ; i--)
                                tid = "0" +tid;
                            Date vaccinationsTid = null;
                            try {
                                vaccinationsTid = dateParser.parse(dato);
                            } catch (ParseException e) {
                                throw new NumberFormatException("Ugyldig værdi (" +dato+tid +") for vaccinationsdato/tid på linie " +lineNbr);
                            }
                            String vaccineType = values.get(4);
                            String vaccinationsSted = values.get(5);
                            VaccinationsAftale aftale = new VaccinationsAftale(cprnr,navn,vaccinationsTid,Integer.parseInt(tid),Vacciner.valueOf(vaccineType),Lokation.valueOf(vaccinationsSted));
                            aftaler.add(aftale);
                            break;
                        default: // Wrong file format
                            throw new IOException("Ugyldigt antal værdier på linie" +lineNbr +". Forventede " +NUMBER_OF_FIELDS_EXPECTED +" værdier, læste " +values.size());
                    }
                }

            }
        } finally {
            if(in != null)
                try { in.close(); } catch(Exception e) { /* Ignore */ };
        }

        return aftaler;
    }
}