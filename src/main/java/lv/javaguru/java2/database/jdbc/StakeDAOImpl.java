package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.domain.Stake;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tyoma17 on 30.03.2016.
 */
@Component
public class StakeDAOImpl extends GenericHibernateDAOImpl<Stake> implements StakeDAO {

    Logger logger = LoggerFactory.getLogger(StakeDAOImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Stake> getAllStakes(Long id) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("userId", id));
        return criteria.list();
    }
}

