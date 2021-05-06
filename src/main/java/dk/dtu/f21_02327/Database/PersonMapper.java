package dk.dtu.f21_02327.Database;

import com.sun.jdi.connect.Connector;
import dk.dtu.f21_02327.Model.Person;

import java.sql.*;

/**
 * Map klasserne er designet til at structurerer SQL queries og actioner.
 * Deres funktioner er ikke meget anderledes fra hinanden.
 * Brug de nedarvet metoder til at skrive statements i.
 *
 * Hold øje med om den database er blevet opdateret.
 * Det kan gøres ved at genkører SQL scriptet i:
 * src/main/resources/SQL.sql
 *
 * Brug hovedsagligt denne klasse som guide til JDBC.
 *
 * @author Hans Christian Leth-Nissen
 */
public class PersonMapper{

    private DBConnection connector;

    public PersonMapper(DBConnection connector) {
        this.connector = connector;
    }


    /**
     * Når du kører denne metode, så bliver gældne person lagt i databasen.
     * Metoden bliver ændret længere inde i projektet.
     * Håber det hjælper.
     *
     * Start med at tage tables som ikke indeholder foreign keys.
     * Personen har en foreign key til vaccinetypen, derfor har jeg brugt:
     * statement.execute("SET foreign_key_checks = 0");
     *
     * @param person
     * @return
     */
    public boolean createPersonInDB(Person person) {
        Connection connection = connector.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = getInsertPersonStatement();

            ps.setString(1,person.getCpr());
            ps.setString(2,person.getPersonNavn());

            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Creating person in db failed! \n" +
                    "Check if database primitives are correct (int, String)");
        }
        return false;
    }

    private static final String SQL_INSERT_PERSON =
            "INSERT IGNORE INTO Person(cpr, personNavn) VALUES (?,?)";

    private PreparedStatement insert_person_stmt = null;

    private PreparedStatement getInsertPersonStatement()
    {
        if(insert_person_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                insert_person_stmt = connection.prepareStatement(
                        SQL_INSERT_PERSON,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return insert_person_stmt;
    }
}
