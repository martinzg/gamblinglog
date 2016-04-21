package lv.javaguru.java2.database;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericHibernateDAOImpl<T> {

    protected Class<T> persistentClass;

    public GenericHibernateDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    protected SessionFactory sessionFactory;

    @Transactional
    public void create(T obj)  throws JDBCException {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Transactional
    public T getById(long id)  throws JDBCException {
        return (T)sessionFactory.getCurrentSession().get(persistentClass, id);
    }

    @Transactional
    public void delete(long id)   throws JDBCException{
        Session session = sessionFactory.getCurrentSession();
        T obj = (T) session.get(persistentClass, id);
        session.delete(obj);
    }

    @Transactional
    public void update(T obj)  throws JDBCException {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Transactional
    public List<T> getAll()  throws JDBCException {
        return sessionFactory.getCurrentSession().createCriteria(persistentClass).list();
    }

}