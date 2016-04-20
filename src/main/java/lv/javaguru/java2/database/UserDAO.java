package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;
import org.hibernate.JDBCException;

public interface UserDAO extends GenericDAO<User> {

    Long getIdByEmail(String email) throws JDBCException;

}
