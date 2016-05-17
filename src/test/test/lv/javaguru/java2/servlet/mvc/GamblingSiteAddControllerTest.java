package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.nio.file.attribute.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.servlet.mvc.GamblingSiteAddController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfigTest.class)
public class GamblingSiteAddControllerTest {

	@Autowired
	private GamblingSiteAddController siteAddController;

	private HttpServletRequest req = mock(HttpServletRequest.class);
	private HttpSession session = mock(HttpSession.class);

    @Test
	public void testSiteAddSuccess() throws Exception {
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(req.getParameter("siteName")).thenReturn("sonya");
		Mockito.when(req.getParameter("siteURL")).thenReturn("www.sonya.lv");
		Mockito.when(req.getParameter("siteDescription")).thenReturn("bla bla bla");
		
		UserPrincipal userPrincipal = Mockito.mock(UserPrincipal.class);
		Mockito.when(userPrincipal.getName()).thenReturn("bla@bla.lv");
		Mockito.when(req.getUserPrincipal()).thenReturn(userPrincipal);
    	
		ModelAndView modelAndView = siteAddController.processRequestPost(req);
		assertEquals("GamblingSiteAdd", modelAndView.getViewName());
		assertEquals("Success!", modelAndView.getModel().get("message"));
    }

	@Test
	public void testSiteAddFailure() throws Exception {
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(req.getParameter("siteName")).thenReturn("sonya");
		Mockito.when(req.getParameter("siteURL")).thenReturn("www.sonya.lv");
		Mockito.when(req.getParameter("siteDescription")).thenReturn("bla bla bla");

		ModelAndView modelAndView = siteAddController.processRequestPost(req);
		assertEquals("GamblingSiteAdd", modelAndView.getViewName());
		assertEquals("Failure!", modelAndView.getModel().get("message"));
	}

	@Test
	public void testSiteAddRedirect() throws Exception {
		Mockito.when(req.getParameter("back")).thenReturn(new String());

		ModelAndView modelAndView = siteAddController.processRequestPost(req);
		assertEquals("Redirect", modelAndView.getViewName());
		assertEquals("/gamblingsites", modelAndView.getModel().get("model"));
	}
}
