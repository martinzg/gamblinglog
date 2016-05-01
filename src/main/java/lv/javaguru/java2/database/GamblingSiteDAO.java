package lv.javaguru.java2.database;

import java.util.List;

import lv.javaguru.java2.domain.GamblingSite;

public interface GamblingSiteDAO extends GenericDAO<GamblingSite> {

	List<GamblingSite> getAllSitesByUserId(Long id);

}
