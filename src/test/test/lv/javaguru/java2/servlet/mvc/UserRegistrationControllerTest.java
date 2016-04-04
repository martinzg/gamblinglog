package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import lv.javaguru.java2.servlet.mvc.UserRegistrationController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class UserRegistrationControllerTest {

    private UserRegistrationController userRegistrationController;
    private static UserDAOImpl userDAO;

    @Mock
    HttpServletRequest req = mock(HttpServletRequest.class);

    @BeforeClass
    public static void cleanUser() throws DBException {
        userDAO = new UserDAOImpl();
        Long id = userDAO.getIdByEmail("martinsgailums@inbox.lv");
        if(id != null){
            userDAO.delete(id);
        }
    }

    @Before
    public void init() throws DBException {
        userRegistrationController = new UserRegistrationController();
    }

    @Test
    public void testSuccessfulUserRegistration() throws Exception {

        doReturn("martins").when(req).getParameter("firstname");
        doReturn("gailums").when(req).getParameter("lastname");
        doReturn("martinsgailums@inbox.lv").when(req).getParameter("email");
        doReturn("h").when(req).getParameter("password");
        doReturn("h").when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/Redirect.jsp", mvcModel.getJspName());
        assertEquals("/java2/login", mvcModel.getData());
        assertEquals("User registered successfully!", mvcModel.getMessage());

    }

    @Test
    public void testUserAlreadyExistsErrorAtUserRegistration() throws Exception {

        doReturn("m").when(req).getParameter("firstname");
        doReturn("g").when(req).getParameter("lastname");
        doReturn(userDAO.getAll().get(0).getEmail()).when(req).getParameter("email");
        doReturn("h").when(req).getParameter("password");
        doReturn("h").when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("User with such email already exists!", mvcModel.getMessage());

    }

    @Test
    public void testPasswordMismatchErrorAtUserRegistration() throws Exception {

        doReturn("m").when(req).getParameter("firstname");
        doReturn("g").when(req).getParameter("lastname");
        doReturn("m@m.lv").when(req).getParameter("email");
        doReturn("a").when(req).getParameter("password");
        doReturn("b").when(req).getParameter("confirm password");

        MVCModel mvcModel = userRegistrationController.processRequestPost(req);
        assertEquals("/UserRegistration.jsp", mvcModel.getJspName());
        assertNull(mvcModel.getData());
        assertEquals("'Password' and 'Confirm Password' do not match!", mvcModel.getMessage());

    }

}