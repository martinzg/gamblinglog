package test.lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.servlet.mvc.GamblingSiteAddController;
import lv.javaguru.java2.servlet.mvc.MVCModel;

public class GamblingSiteAddControllerTest {
	
	private HttpServletRequest req;
	private HttpSession session;
	private GamblingSiteDAO siteDao;

	private GamblingSiteAddController siteAddController;

    @Before
    public void init(){
		req = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		siteDao = mock(GamblingSiteDAO.class);

		siteAddController = new GamblingSiteAddController(siteDao);
    }

    @Test
	public void testSiteAddSuccess() throws Exception {
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute("userId")).thenReturn(1);
		Mockito.when(req.getParameter("siteName")).thenReturn("sonya");
		Mockito.when(req.getParameter("siteURL")).thenReturn("www.sonya.lv");
		Mockito.when(req.getParameter("siteDescription")).thenReturn("bla bla bla");
    	
    	MVCModel mvcModel = siteAddController.processRequestPost(req);
    	assertEquals("/GamblingSiteAdd.jsp", mvcModel.getJspName());
		assertEquals("Success!", mvcModel.getMessage());
    }

	@Test
	public void testSiteAddFailure() throws Exception {
		Mockito.when(req.getSession()).thenReturn(session);
		Mockito.when(req.getParameter("siteName")).thenReturn("sonya");
		Mockito.when(req.getParameter("siteURL")).thenReturn("www.sonya.lv");
		Mockito.when(req.getParameter("siteDescription")).thenReturn("bla bla bla");

		MVCModel mvcModel = siteAddController.processRequestPost(req);
		assertEquals("/GamblingSiteAdd.jsp", mvcModel.getJspName());
		assertEquals("Failure!", mvcModel.getMessage());
	}

	@Test
	public void testSiteAddRedirect() throws Exception {
		Mockito.when(req.getParameter("back")).thenReturn(new String());

		MVCModel mvcModel = siteAddController.processRequestPost(req);
		assertEquals("/Redirect.jsp", mvcModel.getJspName());
		assertEquals("/java2/gamblingsites", mvcModel.getData());
	}
}
