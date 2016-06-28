package test.lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.hibernate.JDBCException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration
public class UserMessageDAOImplTest {

    @Autowired
    private UserMessageDAO userMessageDAO;

    private String userFrom = "userFrom@email.com";
    private String userTo = "userTo@email.com";
    private String message = "This is test message!";
    private Boolean readState = false;

    private UserMessage userMessage;


    @Before
    public void init() throws JDBCException {
        userMessage = createMessage(userFrom, userTo, message, readState);
        userMessageDAO.create(userMessage);
    }

    @After
    public void tearDown() throws JDBCException {
        List<UserMessage> messageListFromDB = userMessageDAO.getMessagesByUserNameFrom(userFrom);
        if (messageListFromDB.size() > 0){
            for (UserMessage item : messageListFromDB){
                userMessageDAO.delete(item.getId());
            }
        }
    }

    @Test
    public void testCreateAndGetOneMessageByUserNameFrom() throws Exception {
        List<UserMessage> messageListFromDB = userMessageDAO.getMessagesByUserNameFrom(userFrom);

        assertEquals(1, messageListFromDB.size());
        assertEquals(userMessage, messageListFromDB.get(0));
    }

    @Test
    public void testCreateAndGetTwoMessagesByUserNameFrom() throws Exception {
        UserMessage userMessageTwo = createMessage(userFrom, "2" + userTo, "2" + message, true);
        userMessageDAO.create(userMessageTwo);
        List<UserMessage> messageListFromDB = userMessageDAO.getMessagesByUserNameFrom(userFrom);

        assertEquals(2, messageListFromDB.size());
        assertEquals(userMessage, messageListFromDB.get(0));
        assertEquals(userMessageTwo, messageListFromDB.get(1));
    }

    @Test
    public void testCreateAndGetOneMessageByUserNameTo() throws Exception {
        List<UserMessage> messageListFromDB = userMessageDAO.getMessagesByUserNameTo(userTo);

        assertEquals(1, messageListFromDB.size());
        assertEquals(userMessage, messageListFromDB.get(0));
    }

    @Test
    public void testGetUnreadMessageCountByUserNameTo() throws Exception {
        UserMessage userMessageTwo = createMessage(userFrom, userTo, message, true);
        userMessageDAO.create(userMessageTwo);

        assertEquals(1, userMessageDAO.getUnreadMessageCountByUserNameTo(userTo));
    }


    @Test
    public void testCreateAndUpdate() throws Exception {
        userMessageDAO.delete(userMessageDAO.getMessagesByUserNameFrom(userFrom).get(0).getId());

        UserMessage userMessage = createMessage("user1@email.com", "user2@email.com", "Message text.", readState);
        userMessageDAO.create(userMessage);
        updateMessage(userMessage);
        userMessageDAO.update(userMessage);
        List<UserMessage> messageListFromDB = userMessageDAO.getMessagesByUserNameFrom(userFrom);

        assertEquals(1, messageListFromDB.size());
        assertEquals(userMessage, messageListFromDB.get(0));
    }

    @Test
    public void testGetAllMessages() throws Exception {
        List<UserMessage> messageListFromDB = userMessageDAO.getAll();

        assertTrue(messageListFromDB.size() >= 1);
        assertEquals(userMessage, getParticularMessageFromList(messageListFromDB));
    }

    @Test
    public void testGetMessageById() throws Exception {
        Long id = userMessageDAO.getMessagesByUserNameFrom(userFrom).get(0).getId();

        assertEquals(userMessage, userMessageDAO.getById(id));
    }

    @Test
    public void testDeleteMessageById() throws Exception {
        Long id = userMessageDAO.getMessagesByUserNameFrom(userFrom).get(0).getId();
        userMessageDAO.delete(id);

        assertEquals(0, userMessageDAO.getMessagesByUserNameFrom(userFrom).size());
    }


    private UserMessage createMessage(String userFrom, String userTo, String message, Boolean readState){
        UserMessage userMessage = new UserMessage();
        userMessage.setUserFrom(userFrom);
        userMessage.setUserTo(userTo);
        userMessage.setDateTime(new Date());
        userMessage.setMessage(message);
        userMessage.setReadState(readState);
        return userMessage;
    }

    private void updateMessage(UserMessage userMessage){
        userMessage.setUserFrom(userFrom);
        userMessage.setUserTo(userTo);
        userMessage.setDateTime(new Date());
        userMessage.setMessage(message);
        userMessage.setReadState(true);
    }

    private UserMessage getParticularMessageFromList(List<UserMessage> userMessageList){
        for (UserMessage item : userMessageList){
            if (item.getUserFrom().equals(userFrom)){
                return item;
            }
        }
        return null;
    }

}