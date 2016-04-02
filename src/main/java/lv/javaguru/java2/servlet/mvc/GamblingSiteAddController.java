package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.jdbc.GamblingSiteDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

public class GamblingSiteAddController implements MVCController {

	@Override
	public MVCModel processRequestGet(HttpServletRequest request) {
		return new MVCModel("/GamblingSiteAdd.jsp", null, null);
	}

	@Override
	public MVCModel processRequestPost(HttpServletRequest request) {

		GamblingSite site = new GamblingSite();
		GamblingSiteDAO siteDAO = new GamblingSiteDAOImpl();

		getGamblingSiteAddData(site, request);

		try {
			siteDAO.create(site);
			return new MVCModel("/GamblingSiteAdd.jsp", null, "Success!");
		} catch (DBException e) {
			e.printStackTrace();
			return new MVCModel("/Redirect.jsp", null, "Failure!");
		}

	}

	private void getGamblingSiteAddData(GamblingSite site, HttpServletRequest request) {
		site.setName(request.getParameter("siteName"));
		site.setURL(request.getParameter("siteURL"));
		site.setDescription(request.getParameter("siteDescription"));
		site.setUserId(Long.parseLong(request.getSession().getAttribute("userId").toString()));
	}
}
