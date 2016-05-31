package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import lv.javaguru.java2.servlet.mvc.LoginController;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration

public class LoginControllerTest {

    private LoginController loginController = new LoginController();

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);

    @Test
    public void testSuccessfulLogin() throws Exception {
        ModelAndView modelAndView = loginController.processGetRequest(req, resp);
        assertEquals("Redirect", modelAndView.getViewName());
        assertEquals("{model=/userprofile}", modelAndView.getModel().toString());
    }

}