package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.domain.Stake;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyoma17 on 30.03.2016.
 */
public class StakeDAOImpl extends DAOImpl implements StakeDAO {

    @Override
    public void create(Stake stake) throws DBException {
        if (stake == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STAKES VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, stake.getDate());
            preparedStatement.setString(2, stake.getUrl());
            preparedStatement.setString(3, stake.getEvent());
            preparedStatement.setString(4, stake.getBetType());
            preparedStatement.setDouble(5, stake.getBetAmount());
            preparedStatement.setDouble(6, stake.getCoefficient());
            preparedStatement.setString(7, stake.getResult());
            preparedStatement.setString(8, stake.getComment());

            preparedStatement.executeLargeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                stake.setStakeID(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute StakeDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Stake getByID(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STAKES WHERE StakeID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Stake stake = null;
            if (resultSet.next()) {
                stake = new Stake();
                stake.setStakeID(resultSet.getLong("Stake ID"));
                stake.setDate(resultSet.getDate("Date"));
                stake.setUrl(resultSet.getString("Url"));
                stake.setEvent(resultSet.getString("Event"));
                stake.setBetType(resultSet.getString("Bet Type"));
                stake.setBetAmount(resultSet.getDouble("Bet Amount"));
                stake.setCoefficient(resultSet.getDouble("Coefficient"));
                stake.setResult(resultSet.getString("Result"));
                stake.setComment(resultSet.getString("Comment"));
            }
            return stake;
        } catch (Throwable e) {
            System.out.println("Exception while execute StakeDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Stake> getAllStakes() throws DBException {
        List<Stake> stakes = new ArrayList<Stake>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STAKES");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stake stake = new Stake();
                stake.setStakeID(resultSet.getLong("Stake ID"));
                stake.setDate(resultSet.getDate("Date"));
                stake.setUrl(resultSet.getString("Url"));
                stake.setEvent(resultSet.getString("Event"));
                stake.setBetType(resultSet.getString("Bet Type"));
                stake.setBetAmount(resultSet.getDouble("Bet Amount"));
                stake.setCoefficient(resultSet.getDouble("Coefficient"));
                stake.setResult(resultSet.getString("Result"));
                stake.setComment(resultSet.getString("Comment"));
                stakes.add(stake);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list StakeDAOImpl.getAllStakes()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return stakes;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from STAKES where UserID  = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute StakeDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Stake stake) throws DBException {
        if (stake == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update STAKES set Date = ?, Url = ?, Event = ?, BetType = ?, BetAmount = ?, Coefficient = ?, Result = ?, Comment = ?, where UserID = ?");
            preparedStatement.setDate(1, stake.getDate());
            preparedStatement.setString(2, stake.getUrl());
            preparedStatement.setString(3, stake.getEvent());
            preparedStatement.setString(4, stake.getBetType());
            preparedStatement.setDouble(5, stake.getBetAmount());
            preparedStatement.setDouble(6, stake.getCoefficient());
            preparedStatement.setString(7, stake.getResult());
            preparedStatement.setString(8, stake.getComment());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute StakeDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
