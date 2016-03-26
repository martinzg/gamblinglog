package lv.javaguru.java2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.jdbc.GamblingSiteDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

public class GamblingSiteAddServlet extends HttpServlet {

	private static final long serialVersionUID = -6621753465033498446L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GamblingSite site = new GamblingSite();
		GamblingSiteDAO siteDAO = new GamblingSiteDAOImpl();

		getGamblingSiteAddData(site, request);

		try {
			siteDAO.create(site);
			response.sendRedirect("/java2/gambling-site-add?param=Success");
		} catch (DBException e) {
			e.printStackTrace();
			response.sendRedirect("/java2/gambling-site-add?param=Failure");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/GamblingSiteAdd.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void getGamblingSiteAddData(GamblingSite site, HttpServletRequest req) {
		site.setName(req.getParameter("siteName"));
		site.setURL(req.getParameter("siteURL"));
		site.setDescription(req.getParameter("siteDescription"));

		// FIXME: Get userId from session.
		site.setUserId(1002L);
	}
}
