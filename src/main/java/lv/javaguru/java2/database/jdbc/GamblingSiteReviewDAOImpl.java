package lv.javaguru.java2.database.jdbc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.database.GamblingSiteReviewDAO;
import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.domain.GamblingSiteReview;

@Component
public class GamblingSiteReviewDAOImpl extends GenericHibernateDAOImpl<GamblingSiteReview>
		implements GamblingSiteReviewDAO {

	Logger logger = LoggerFactory.getLogger(GamblingSiteReviewDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<GamblingSiteReview> getAllReviewsByUserId(Long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq("userId", id));
		return criteria.list();
	}



}
