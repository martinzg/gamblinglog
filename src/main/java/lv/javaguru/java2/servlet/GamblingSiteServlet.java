package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.jdbc.GamblingSiteDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GamblingSiteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		if (req.getParameter("add site") != null) {
			resp.sendRedirect("/java2/gambling-site-add?id=" + req.getParameter("id"));
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Long id = Long.parseLong(session.getAttribute("userId").toString());
		GamblingSiteDAO gamblingSiteDAO = new GamblingSiteDAOImpl();

		try {
			List<GamblingSite> gamblingSiteList = gamblingSiteDAO.getAllSitesByUserId(id);
			req.setAttribute("gamblingSiteList", gamblingSiteList);
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/GamblingSites.jsp");
			requestDispatcher.forward(req, resp);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
