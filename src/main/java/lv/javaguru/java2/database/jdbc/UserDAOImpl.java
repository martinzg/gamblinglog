package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Component
public class UserDAOImpl extends GenericHibernateDAOImpl<User> implements UserDAO {

    Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Transactional
    public Long getIdByEmail(String email) throws JDBCException {
        String hql = "select UserID from USERS where Email = :Email";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("Email", email);
        if (query.list().size() > 0){
            BigInteger id = (BigInteger)query.list().get(0);
            return id.longValue();
        }
        return null;
    }

    @Override
    @Transactional
    public void create(User obj)  throws JDBCException {
        sessionFactory.getCurrentSession().save(obj);
        createUserRole(obj);
    }

    @Override
    @Transactional
    public void delete(long id)  throws JDBCException {
        Session session = sessionFactory.getCurrentSession();
        User obj = (User) session.get(persistentClass, id);
        session.delete(obj);
        deleteFromUserRoles(obj);
        deleteFromGamblingSites(obj);
    }

    private void createUserRole (User obj){
        String hql = "insert into USER_ROLES values (default, :UserID, :Email, 1002, 'GamblingLogRole')";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("UserID", obj.getUserId());
        query.setParameter("Email", obj.getEmail());
        query.executeUpdate();
    }

    private void deleteFromUserRoles (User obj){
        String hql = "delete from USER_ROLES where UserID = :UserID";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("UserID", obj.getUserId());
        query.executeUpdate();
    }

    private void deleteFromGamblingSites (User obj){
        String hql = "delete from SITES where UserID = :UserID";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("UserID", obj.getUserId());
        query.executeUpdate();
    }

}
