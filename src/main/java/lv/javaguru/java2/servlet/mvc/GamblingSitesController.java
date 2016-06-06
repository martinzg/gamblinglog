package lv.javaguru.java2.servlet.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;

@Controller
public class GamblingSitesController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GamblingSiteDAO gamblingSiteDAO;

	@RequestMapping(value = "gamblingsites", method = { RequestMethod.GET })
	public ModelAndView processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
		Long id = userDAO.getIdByEmail(req.getUserPrincipal().getName());
		List<GamblingSite> gamblingSiteList = gamblingSiteDAO.getAllSitesByUserId(id);
		return new ModelAndView("GamblingSites", "gamblingSiteList", gamblingSiteList);
    }

	@RequestMapping(value = "gamblingsites", method = { RequestMethod.POST })
	public ModelAndView processRequestPost(HttpServletRequest req) {
        if (req.getParameter("add site") != null) {
			return new ModelAndView("Redirect", "model", "/gambling-site-add");
        }
        else {
			String siteIdsParameter = req.getParameter("siteIds");
			if (siteIdsParameter != null && !siteIdsParameter.isEmpty()) {
				String[] siteIds = siteIdsParameter.split(",");
				for (String siteId : siteIds) {
					if (!siteId.isEmpty()) {
						gamblingSiteDAO.delete(Long.valueOf(siteId));
					}
				}
			}
			return new ModelAndView("Redirect", "model", "/gamblingsites");
        }
    }

}
