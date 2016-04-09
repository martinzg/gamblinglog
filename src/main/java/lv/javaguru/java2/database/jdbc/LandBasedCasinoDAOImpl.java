package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.LandBasedCasino;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LandBasedCasinoDAOImpl extends DAOImpl {

    Logger logger = LoggerFactory.getLogger(LandBasedCasinoDAOImpl.class);

    public List<LandBasedCasino> getAll() {
        Connection connection;
        List<LandBasedCasino> landBasedCasinos = new ArrayList<>();
        ResultSet resultSet;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LANDBASEDCASINO");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LandBasedCasino landBasedCasino = new LandBasedCasino();
                landBasedCasino.setName(resultSet.getString("Name"));
                landBasedCasino.setDescription(resultSet.getString("Description"));
                landBasedCasino.setId(resultSet.getLong("ID"));
                landBasedCasinos.add(landBasedCasino);
            }
        }
        catch (DBException ex) {
            logger.error("DB exception");
            ex.printStackTrace();
        }
        catch (SQLException ex2) {
            logger.error("SQL exception");
            ex2.printStackTrace();
        }
        return landBasedCasinos;
    }
}
