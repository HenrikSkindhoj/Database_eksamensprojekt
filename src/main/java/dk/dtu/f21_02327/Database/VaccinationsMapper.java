package dk.dtu.f21_02327.Database;

import dk.dtu.f21_02327.Model.Vacciner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class VaccinationsMapper extends DBMap{

    private DBConnection connector;

    public VaccinationsMapper(DBConnection connector) {
        this.connector = connector;
    }

    public boolean createVaccinationInDB(Vacciner vacciner){


        return false;
    }

    private static final String SQL_INSERT_VACCINES =
            "INSERT INTO Vaccines(vaccinationTypeID, vaccinationTypeName, vaccinePrice) VALUES (?,?,?)";

    private PreparedStatement insert_vaccines_stmt = null;

    private PreparedStatement getInsertVaccinesStatement()
    {
        if(insert_vaccines_stmt == null)
        {
            Connection connection = connector.getConnection();
            try {
                insert_vaccines_stmt = connection.prepareStatement(
                        SQL_INSERT_VACCINES,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return insert_vaccines_stmt;
    }

    @Override
    public boolean createToDB(Object e) {
        return false;
    }
}
