package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.jdbc.GamblingSiteDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

public class GamblingSiteAddController implements MVCController {

	private final GamblingSiteDAO siteDAO;

	public GamblingSiteAddController() {
		this(new GamblingSiteDAOImpl());
	}

	public GamblingSiteAddController(GamblingSiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}

	@Override
	public MVCModel processRequestGet(HttpServletRequest request) {
		return new MVCModel("/GamblingSiteAdd.jsp", null, null);
	}

	@Override
	public MVCModel processRequestPost(HttpServletRequest request) {
		if (request.getParameter("back") != null) {
			return new MVCModel("/Redirect.jsp", "/java2/gamblingsites", null);
		}

		try {
			GamblingSite site = new GamblingSite();
			getGamblingSiteAddData(site, request);
			siteDAO.create(site);
			return new MVCModel("/GamblingSiteAdd.jsp", null, "Success!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MVCModel("/GamblingSiteAdd.jsp", null, "Failure!");
		}

	}

	private void getGamblingSiteAddData(GamblingSite site, HttpServletRequest request) {
		site.setName(request.getParameter("siteName"));
		site.setURL(request.getParameter("siteURL"));
		site.setDescription(request.getParameter("siteDescription"));
		site.setUserId(Long.parseLong(request.getSession().getAttribute("userId").toString()));
	}
}
