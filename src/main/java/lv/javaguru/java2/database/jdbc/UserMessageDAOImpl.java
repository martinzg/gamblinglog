package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import org.hibernate.*;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Component;
import javax.transaction.Transactional;


@Component
public class UserMessageDAOImpl extends GenericHibernateDAOImpl<UserMessage> implements UserMessageDAO {

    @SuppressWarnings("unchecked")
    @Transactional
    public List<UserMessage> getMessagesByUserNameFrom(String userName) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("userFrom", userName));
        criteria.addOrder(Order.desc("dateTime"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<UserMessage> getMessagesByUserNameTo(String userName) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("userTo", userName));
        criteria.addOrder(Order.desc("dateTime"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public UserMessage getMessageById(Long id) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("id", id));
        if (criteria.list().size() > 0){
            return (UserMessage) criteria.list().get(0);
        }
        else {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public int getUnreadMessageCountByUserNameTo(String userName) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("userTo", userName));
        criteria.add(Restrictions.eq("readState", false));
        return Integer.valueOf(criteria.uniqueResult().toString());
    }

}
