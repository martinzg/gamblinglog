package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import lv.javaguru.java2.servlet.mvc.UserRegistrationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class UserRegistrationControllerTest {

    @Autowired
    private UserRegistrationController userRegistrationController;

    String firstName = "firstName";
    String lastName = "lastName";
    private String email = "email@email.com";
    private String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    UserDAO userDAO = mock(UserDAO.class);

    @Before
    public void init() throws DBException {
        userRegistrationController.setUserDAO(userDAO);
        doReturn(firstName).when(req).getParameter("firstname");
        doReturn(lastName).when(req).getParameter("lastname");
        doReturn(email).when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");
    }

    @Test
    public void testSuccessfulUserRegistration() throws Exception {
        doReturn(password).when(req).getParameter("confirm password");
        doReturn(session).when(req).getSession();
        doReturn(null).when(userDAO).getIdByEmail(email);

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/login", mvcModel.getData());
        assertNull(mvcModel.getMessage());
    }

    @Test
    public void testUserAlreadyExistsErrorAtUserRegistration() throws Exception {
        doReturn(password).when(req).getParameter("confirm password");
        doReturn(1L).when(userDAO).getIdByEmail(email);

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("User with such email already exists!", mvcModel.getMessage());
    }

    @Test
    public void testPasswordMismatchErrorAtUserRegistration() throws Exception {
        doReturn(password + "1").when(req).getParameter("confirm password");
        doReturn(null).when(userDAO).getIdByEmail(email);

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("'Password' and 'Confirm Password' do not match!", mvcModel.getMessage());
    }

}