package lv.javaguru.java2.database;

import java.util.List;

import lv.javaguru.java2.domain.GamblingSite;

public interface GamblingSiteDAO {

	void create(GamblingSite site) throws DBException;

	GamblingSite getById(Long id) throws DBException;

	void delete(Long id) throws DBException;

	void update(GamblingSite site) throws DBException;

	List<GamblingSite> getAllSitesByUserId(Long id) throws DBException;

}
