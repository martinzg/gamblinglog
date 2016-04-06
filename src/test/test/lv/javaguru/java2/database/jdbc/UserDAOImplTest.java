package test.lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private UserDAOImpl userDAO = new UserDAOImpl();
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email@email.com";
    private String password = "password";

    @After
    public void init() throws DBException {
        while (userDAO.getIdByEmail(email) != null) {
            userDAO.delete(userDAO.getIdByEmail(email));
        }
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser(firstName, lastName, email, password);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());

        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getEmail(), userFromDB.getEmail());
        assertEquals(user.getPassword(), userFromDB.getPassword());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = createUser(firstName, lastName, email, password);
        User user2 = createUser(firstName, lastName, email, password);
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertTrue(users.size() >= 2);
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