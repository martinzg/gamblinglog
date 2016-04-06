package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.UserRegistrationController;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class UserRegistrationControllerTest {

    private UserRegistrationController userRegistrationController;
    private static UserDAOImpl userDAO;
    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String email = "email@email.com";
    private static String emailSuccess = "email@emailSuccess.com";
    private static String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @BeforeClass
    public static void createTestUser() throws DBException {
        userDAO = new UserDAOImpl();
        User user = UserCreator.createUser(firstName, lastName, email, password);
        userDAO.create(user);
    }

    @AfterClass
    public static void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
        userDAO.delete(userDAO.getIdByEmail(emailSuccess));
    }

    @Before
    public void init() throws DBException {
        userRegistrationController = new UserRegistrationController();
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
        assertEquals("/java2/login", mvcModel.getData());
        assertEquals("User registered successfully!", mvcModel.getMessage());
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