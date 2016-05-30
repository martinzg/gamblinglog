package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserMessage;
import org.hibernate.JDBCException;
import java.util.List;

public interface UserMessageDAO extends GenericDAO<UserMessage> {

    List<UserMessage> getMessagesByUserNameFrom(String userName) throws JDBCException;

    List<UserMessage> getMessagesByUserNameTo(String userName) throws JDBCException;

    UserMessage getMessageById(Long id) throws JDBCException;

}
