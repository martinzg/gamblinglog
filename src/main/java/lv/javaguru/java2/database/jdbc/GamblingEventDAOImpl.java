package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.GamblingType;
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
public class GamblingEventDAOImpl extends DAOImpl {

    Logger logger = LoggerFactory.getLogger(GamblingEventDAOImpl.class);

    public List<GamblingType> getAll() {
        Connection connection;
        List<GamblingType> gamblingTypes = new ArrayList<>();
        ResultSet resultSet;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GAMBLINGTYPE");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GamblingType gamblingType = new GamblingType();
                gamblingType.setName(resultSet.getString("Name"));
                gamblingType.setId(resultSet.getLong("ID"));
                gamblingTypes.add(gamblingType);
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
        return gamblingTypes;
    }
}
