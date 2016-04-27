package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)

public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    String email = "email@email.com";
    String password = "password";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @Test
    public void testSuccessfulLogin() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(password).when(req).getParameter("password");

        MVCModel mvcModel = loginController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/userprofile", mvcModel.getData());
        assertNull(mvcModel.getMessage());
    }

}