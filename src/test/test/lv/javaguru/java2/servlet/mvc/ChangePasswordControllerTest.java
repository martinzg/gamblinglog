package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.mvc.ChangePasswordController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class ChangePasswordControllerTest {

    @Autowired
    private ChangePasswordController changePasswordController;

    private String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    UserDAO userDAO = mock(UserDAO.class);
    User user = mock(User.class);

    @Before
    public void init() throws DBException {
        changePasswordController.setUserDAO(userDAO);
        doReturn(session).when(req).getSession();
    }

    @Test
    public void testSuccessfulPasswordChange() throws Exception {
        doReturn(password).when(req).getParameter("password");
        doReturn(password).when(req).getParameter("confirm password");
        doReturn("1").when(session).getId();
        doReturn("1").when(req).getParameter("link");
        doReturn(1002L).when(session).getAttribute("userId");
        doReturn(user).when(userDAO).getById(1002L);

        MVCModel mvcModel = changePasswordController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/login", mvcModel.getData());
        assertNull(mvcModel.getMessage());
    }

    @Test
    public void testPasswordMismatchAtPasswordChange() throws Exception {
        doReturn(password).when(req).getParameter("password");
        doReturn(password + "1").when(req).getParameter("confirm password");
        doReturn("1").when(session).getId();
        doReturn("1").when(req).getParameter("link");

        MVCModel mvcModel = changePasswordController.processRequestPost(req);
        assertEquals("/ChangePassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("'New Password' and 'Confirm New Password' do not match!", mvcModel.getMessage());
    }

    @Test
    public void testResetLinkExpiredAtPasswordChange() throws Exception {
        doReturn("1").when(session).getId();
        doReturn("2").when(req).getParameter("link");

        MVCModel mvcModel = changePasswordController.processRequestPost(req);
        assertEquals("/ChangePassword.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Your password reset link has expired!", mvcModel.getMessage());
    }

}