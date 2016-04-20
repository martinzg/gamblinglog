package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
public class UserDAOImpl extends GenericHibernateDAOImpl<User> implements UserDAO {

    Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Transactional
    public Long getIdByEmail(String email) throws JDBCException {
        return null; // (Long) sessionFactory.getCurrentSession().get(persistentClass, email);
    }

    @Transactional
    public void create(User obj)  throws JDBCException {
        sessionFactory.getCurrentSession().save(obj);
        String hql = "insert into USER_ROLES values (default, :UserID, :Email, 1002, 'GamblingLogRole')";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("UserID", obj.getUserId());
        query.setParameter("Email", obj.getEmail());
        query.executeUpdate();
    }

}
