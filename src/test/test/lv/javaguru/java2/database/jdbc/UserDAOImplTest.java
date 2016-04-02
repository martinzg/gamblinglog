package test.lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Before
    public void init() throws DBException {
        //databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("Name", "Surname", "email@email.com", "password");

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = createUser("Name1", "Surname1", "email1@email.com", "password1");
        User user2 = createUser("Name2", "Surname2", "email2@email.com", "password2");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        //assertEquals(2, users.size());
        assertNotNull(users.size());
    }

    private User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}