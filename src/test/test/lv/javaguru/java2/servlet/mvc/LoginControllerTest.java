package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserDAO userDAO;

    private String email = "email@email.com";
    private String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    @Before
    public void createTestUser() throws DBException {
        User user = UserCreator.createUser("firstName", "lastName", email, password);
        userDAO.create(user);
    }

    @After
    public void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/userprofile", mvcModel.getData());
        assertNull(mvcModel.getMessage());
    }
/*
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
*/
}