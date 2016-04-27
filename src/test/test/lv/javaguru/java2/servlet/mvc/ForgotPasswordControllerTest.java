package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
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

    private String email = "email@email.com";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    UserDAO userDAO = mock(UserDAO.class);

    @Before
    public void init() throws DBException {
        forgotPasswordController.setUserDAO(userDAO);
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(session).when(req).getSession();
        doReturn(1002L).when(userDAO).getIdByEmail(email);

        MVCModel mvcModel = forgotPasswordController.processRequestPost(req);
        assertEquals("/ForgotPassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Password reset link has been sent to your email!", mvcModel.getMessage());
    }

    @Test
    public void testInvalidEmailRequest() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(null).when(userDAO).getIdByEmail(email);

        MVCModel mvcModel = forgotPasswordController.processRequestPost(req);
        assertEquals("/ForgotPassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("There is no user with such Email!", mvcModel.getMessage());
    }

}