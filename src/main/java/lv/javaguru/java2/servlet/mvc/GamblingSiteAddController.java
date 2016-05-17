package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;

@Controller
public class GamblingSiteAddController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GamblingSiteDAO siteDAO;

	@RequestMapping(value = "gambling-site-add", method = { RequestMethod.GET })
	public ModelAndView processRequestGet(HttpServletRequest request, HttpServletResponse resp) {
		return new ModelAndView("GamblingSiteAdd", "model", null);
	}

	@RequestMapping(value = "gambling-site-add", method = { RequestMethod.POST })
	public ModelAndView processRequestPost(HttpServletRequest request) {
		if (request.getParameter("back") != null) {
			return new ModelAndView("Redirect", "model", "/gamblingsites");
		}

		try {
			GamblingSite site = new GamblingSite();
			getGamblingSiteAddData(site, request);
			siteDAO.create(site);
			return new ModelAndView("GamblingSiteAdd", "message", "Success!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("GamblingSiteAdd", "message", "Failure!");
		}

	}

	private void getGamblingSiteAddData(GamblingSite site, HttpServletRequest request) throws DBException {
		site.setName(request.getParameter("siteName"));
		site.setURL(request.getParameter("siteURL"));
		site.setDescription(request.getParameter("siteDescription"));
		site.setUserId(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
	}
}
