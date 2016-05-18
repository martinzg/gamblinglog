package lv.javaguru.java2.database;

import java.util.List;

import lv.javaguru.java2.domain.GamblingSiteReview;

public interface GamblingSiteReviewDAO extends GenericDAO<GamblingSiteReview> {

	List<GamblingSiteReview> getAllReviewsByUserId(Long id);

}
