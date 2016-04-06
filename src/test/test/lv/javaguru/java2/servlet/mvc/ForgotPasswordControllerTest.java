package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.ForgotPasswordController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ForgotPasswordControllerTest {

    private ForgotPasswordController forgotPasswordController;
    private static UserDAO userDAO;
    private static String email = "email@email.com";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    @BeforeClass
    public static void createTestUser() throws DBException {
        userDAO = new UserDAOImpl();
        User user = UserCreator.createUser("firstName", "lastName", email, "password");
        userDAO.create(user);
    }

    @AfterClass
    public static void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
    }

    @Before
    public void init() throws DBException {
        forgotPasswordController = new ForgotPasswordController();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = forgotPasswordController.processRequestPost(req);
        assertEquals("/ForgotPassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Password reset link has been sent to your email!", mvcModel.getMessage());
    }

    @Test
    public void testInvalidEmailRequest() throws Exception {
        doReturn("invalid@email.com").when(req).getParameter("email");

        MVCModel mvcModel = forgotPasswordController.processRequestPost(req);
        assertEquals("/ForgotPassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("There is no user with such Email!", mvcModel.getMessage());
    }

}