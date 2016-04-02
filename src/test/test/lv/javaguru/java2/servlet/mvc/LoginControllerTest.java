package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import lv.javaguru.java2.servlet.mvc.LoginController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginControllerTest {

    private LoginController loginController;

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    @Before
    public void init(){
        loginController = new LoginController();
    }

    @Test
    public void testSuccessfulLogin() throws Exception {

        doReturn("martinsgailums@gmail.com").when(req).getParameter("email");
        doReturn("h").when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/java2/userprofile", mvcModel.getData());
        assertNull(mvcModel.getMessage());

    }

    @Test
    public void testInvalidEmailLogin() throws Exception {

        doReturn("martins@martins.com").when(req).getParameter("email");
        doReturn("h").when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/UserLogin.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Invalid Email!", mvcModel.getMessage());

    }

    @Test
    public void testInvalidPasswordLogin() throws Exception {

        doReturn("martinsgailums@gmail.com").when(req).getParameter("email");
        doReturn("test").when(req).getParameter("password");
        doReturn(session).when(req).getSession();

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/UserLogin.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("Invalid Password!", mvcModel.getMessage());

    }

}