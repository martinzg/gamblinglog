package lv.javaguru.java2.database.jdbc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

@Component
public class GamblingSiteDAOImpl extends GenericHibernateDAOImpl<GamblingSite> implements GamblingSiteDAO {

	Logger logger = LoggerFactory.getLogger(GamblingSiteDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<GamblingSite> getAllSitesByUserId(Long id) throws JDBCException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq("userId", id));
		return criteria.list();
	}
}
