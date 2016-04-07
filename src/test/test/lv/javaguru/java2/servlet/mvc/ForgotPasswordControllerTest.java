package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.ForgotPasswordController;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class ForgotPasswordControllerTest {

    @Autowired
    private ForgotPasswordController forgotPasswordController;

    @Autowired
    private UserDAO userDAO;

    private String email = "email@email.com";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    @Before
    public void createTestUser() throws DBException {
        User user = UserCreator.createUser("firstName", "lastName", email, "password");
        userDAO.create(user);
    }

    @After
    public void tearDown() throws DBException {
        userDAO.delete(userDAO.getIdByEmail(email));
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