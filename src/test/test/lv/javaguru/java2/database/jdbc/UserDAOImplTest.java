package test.lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.After;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.lv.javaguru.java2.servlet.mvc.UserCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email@email.com";
    private String password = "password";
    private Boolean image = false;

    @After
    public void tearDown() throws DBException {
        while (userDAO.getIdByEmail(email) != null) {
            userDAO.delete(userDAO.getIdByEmail(email));
        }
    }

    @Test
    public void testCreate() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, email, password, image);
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
        User user1 = UserCreator.createUser(firstName, lastName, email, password, image);
        User user2 = UserCreator.createUser(firstName, lastName, email, password, image);
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertTrue(users.size() >= 2);
    }

    @Test
    public void testGetUserById() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, email, password, image);
        userDAO.create(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }

    @Test
    public void testGetUserByIdNull() throws DBException {
        assertNull(userDAO.getById(1L));
    }

    @Test
    public void testGetUserIdByEmail() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, email, password, image);
        userDAO.create(user);
        assertEquals(Long.valueOf(user.getUserId()), userDAO.getIdByEmail(email));
    }

    @Test
    public void testGetUserIdByEmailNull() throws DBException {
        assertNull(userDAO.getIdByEmail(email));
    }

    @Test
    public void testGetAll() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, email, password, image);
        userDAO.create(user);
        User userFromDb = null;
        List<User> userListFromDb = userDAO.getAll();
        for (User item : userListFromDb){
            if (item.getUserId() == user.getUserId()){
                userFromDb = item;
            }
        }
        assertTrue(userListFromDb.size() >= 1);
        assertEquals(user, userFromDb);
    }

    @Test
    public void testDeleteUser() throws DBException {
        int userListSize = userDAO.getAll().size();
        User user = UserCreator.createUser(firstName, lastName, email, password, image);
        userDAO.create(user);
        userDAO.delete(userDAO.getIdByEmail(email));
        assertNull(userDAO.getIdByEmail(email));
        assertEquals(userListSize, userDAO.getAll().size());
    }

    @Test
    public void testUpdateUser() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, "new" + email, password, image);
        userDAO.create(user);
        user = updateUser(user);
        userDAO.update(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }

    private User updateUser (User user){
        user.setFirstName("1" + firstName);
        user.setLastName("1" + lastName);
        user.setEmail(email);
        user.setPassword("1" + password);
        return user;
    }

}