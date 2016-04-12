package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import lv.javaguru.java2.servlet.mvc.UserRegistrationController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class UserRegistrationControllerTest {

    @Autowired
    private UserRegistrationController userRegistrationController;

    @Autowired
    private UserDAOImpl userDAO;

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email@email.com";
    private String password = "password";
    String emailSuccess = "email@emailSuccess.com";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @Before
    public void createTestUser() throws DBException {
        User user = UserCreator.createUser(firstName, lastName, email, password);
        userDAO.create(user);
    }

    @After
    public void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
    }

    @Test
    public void testSuccessfulUserRegistration() throws Exception {
        doReturn("testName").when(req).getParameter("firstname");
        doReturn("testLastName").when(req).getParameter("lastname");
        doReturn(emailSuccess).when(req).getParameter("email");
        doReturn("pass").when(req).getParameter("password");
        doReturn("pass").when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/login", mvcModel.getData());
        assertEquals("User registered successfully!", mvcModel.getMessage());

        userDAO.delete(userDAO.getIdByEmail(emailSuccess));
    }

    @Test
    public void testUserAlreadyExistsErrorAtUserRegistration() throws Exception {
        doReturn(firstName).when(req).getParameter("firstname");
        doReturn(lastName).when(req).getParameter("lastname");
        doReturn(email).when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");
        doReturn(password).when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("User with such email already exists!", mvcModel.getMessage());
    }

    @Test
    public void testPasswordMismatchErrorAtUserRegistration() throws Exception {
        doReturn("m").when(req).getParameter("firstname");
        doReturn("g").when(req).getParameter("lastname");
        doReturn("m@m.lv").when(req).getParameter("email");
        doReturn("a").when(req).getParameter("password");
        doReturn("b").when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("'Password' and 'Confirm Password' do not match!", mvcModel.getMessage());
    }

}