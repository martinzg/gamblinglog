package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;

@Component
public class GamblingSiteAddController implements MVCController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GamblingSiteDAO siteDAO;

	@Override
	public MVCModel processRequestGet(HttpServletRequest request, HttpServletResponse resp) {
		return new MVCModel("/GamblingSiteAdd.jsp", null, null);
	}

	@Override
	public MVCModel processRequestPost(HttpServletRequest request) {
		if (request.getParameter("back") != null) {
			return new MVCModel("/Redirect.jsp", "/gamblingsites", null);
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

	private void getGamblingSiteAddData(GamblingSite site, HttpServletRequest request) throws DBException {
		site.setName(request.getParameter("siteName"));
		site.setURL(request.getParameter("siteURL"));
		site.setDescription(request.getParameter("siteDescription"));
		site.setUserId(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
	}
}
