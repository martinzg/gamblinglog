package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.ImageDAO;
import lv.javaguru.java2.domain.Image;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ImageDAOImpl extends GenericHibernateDAOImpl<Image> implements ImageDAO {

    @Transactional
    public Image getImageByUserId(Long userID) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("userId", userID));
        return criteria.list().size() == 1 ? (Image)criteria.list().get(0) : null;
    }

}
