package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.servlet.mvc.MessagesNewController;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import lv.javaguru.java2.servlet.mvc.UserRegistrationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.security.acl.PrincipalImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration

public class MessagesNewControllerTest {

    private MessagesNewController messagesNewController = new MessagesNewController();

    String recipient = "email@email.com";
    String comment = "Comment";

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);
    UserDAO userDAO = mock(UserDAO.class);
    UserMessageDAO messageDAO = mock(UserMessageDAO.class);

    @Before
    public void init() throws DBException {
        messagesNewController.setUserDAO(userDAO);
        messagesNewController.setUserMessageDAO(messageDAO);
    }

    @Test
    public void testProcessPostRequest() throws Exception {
        doReturn("1").when(req).getParameter("send");
        doReturn(recipient).when(req).getParameter("recipient");
        doReturn(comment).when(req).getParameter("comment");
        doReturn(1L).when(userDAO).getIdByEmail(recipient);

        //doReturn(principal).when(req).getUserPrincipal().getName();

    }
}