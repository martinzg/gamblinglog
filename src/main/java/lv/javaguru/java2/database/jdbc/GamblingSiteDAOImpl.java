package lv.javaguru.java2.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.domain.GamblingSite;

@Component
public class GamblingSiteDAOImpl extends DAOImpl implements GamblingSiteDAO {

	Logger logger = LoggerFactory.getLogger(GamblingSiteDAOImpl.class);

	@Override
	public void create(GamblingSite site) throws DBException {
		if (site == null) {
			return;
		}

		Connection connection = null;

		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into SITES values (default, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, site.getName());
			preparedStatement.setString(2, site.getURL());
			preparedStatement.setString(3, site.getDescription());
			preparedStatement.setLong(4, site.getUserId());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				site.setId(rs.getLong(1));
			}
		} catch (Throwable e) {
			logger.error("Exception while execute SiteDAOImpl.create()");
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			closeConnection(connection);
		}

	}

	@Override
	public GamblingSite getById(Long id) throws DBException {
		Connection connection = null;

		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from SITES where SiteID = ?");
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			GamblingSite site = null;
			if (resultSet.next()) {
				site = new GamblingSite();
				site.setId(resultSet.getLong("ID"));
				site.setName(resultSet.getString("Name"));
				site.setURL(resultSet.getString("URL"));
				site.setDescription(resultSet.getString("Description"));
				site.setUserId(resultSet.getLong("UserID"));
			}
			return site;
		} catch (Throwable e) {
			logger.error("Exception while execute GamblingSiteDAOImpl.getById()");
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void delete(Long id) throws DBException {

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from SITES where ID = ?");
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (Throwable e) {
			logger.error("Exception while execute GamblingSiteDAOImpl.delete()");
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			closeConnection(connection);
		}

	}

	@Override
	public void update(GamblingSite site) throws DBException {
		if (site == null) {
			return;
		}

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update SITES set Name = ?, URL = ?, Description = ?, UserID = ? " + "where ID = ?");
			preparedStatement.setString(1, site.getName());
			preparedStatement.setString(2, site.getURL());
			preparedStatement.setString(3, site.getDescription());
			preparedStatement.setLong(4, site.getId());
			preparedStatement.setLong(5, site.getUserId());
			preparedStatement.executeUpdate();
		} catch (Throwable e) {
			logger.error("Exception while execute GamblingSiteDAOImpl.update()");
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<GamblingSite> getAllSitesByUserId(Long id) throws DBException {
		List<GamblingSite> sites = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from SITES where UserID = ?");
				preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				GamblingSite site = new GamblingSite();
				site.setId(resultSet.getLong("ID"));
				site.setName(resultSet.getString("Name"));
				site.setURL(resultSet.getString("URL"));
				site.setDescription(resultSet.getString("Description"));
				site.setUserId(resultSet.getLong("UserID"));
				sites.add(site);
			}
		} catch (Throwable e) {
			logger.error("Exception while getting customer list UserDAOImpl.getList()");
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			closeConnection(connection);
		}
		return sites;
	}

}
