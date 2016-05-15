package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.mvc.ForgotPasswordController;
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
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration

public class ForgotPasswordControllerTest {

    private ForgotPasswordController forgotPasswordController = new ForgotPasswordController();

    private String email = "email@email.com";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);
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

        ModelAndView modelAndView = forgotPasswordController.processPostRequest(req, resp);
        assertEquals("ForgotPassword", modelAndView.getViewName());
        assertEquals("{model=Password reset link has been sent to your email!}", modelAndView.getModel().toString());
    }

    @Test
    public void testInvalidEmailRequest() throws Exception {
        doReturn(email).when(req).getParameter("email");
        doReturn(null).when(userDAO).getIdByEmail(email);

        ModelAndView modelAndView = forgotPasswordController.processPostRequest(req, resp);
        assertEquals("ForgotPassword", modelAndView.getViewName());
        assertEquals("{model=There is no user with such Email!}", modelAndView.getModel().toString());
    }

}