package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Image;
import org.hibernate.JDBCException;

public interface ImageDAO extends GenericDAO<Image> {

    Image getImageByUserId(Long userId) throws JDBCException;

}
