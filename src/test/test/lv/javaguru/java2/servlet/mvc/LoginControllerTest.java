package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginControllerTest {

    private LoginController loginController;
    private static UserDAO userDAO;
    private static String email = "email@email.com";
    private static String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    @BeforeClass
    public static void createTestUser() throws DBException {
        userDAO = new UserDAOImpl();
        User user = UserCreator.createUser("firstName", "lastName", email, password);
        userDAO.create(user);
    }

    @AfterClass
    public static void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
    }

    @Before
    public void init() throws DBException {
        loginController = new LoginController();
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/java2/userprofile", mvcModel.getData());
        assertNull(mvcModel.getMessage());
    }

    @Test
    public void testInvalidEmailLogin() throws Exception {
        doReturn("invalid@email.com").when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/UserLogin.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Invalid Email!", mvcModel.getMessage());
    }

    @Test
    public void testInvalidPasswordLogin() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn("invalidPassword").when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/UserLogin.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Invalid Password!", mvcModel.getMessage());
    }

}