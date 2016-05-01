package lv.javaguru.java2.servlet.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;


@Component
public class GamblingSitesController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GamblingSiteDAO gamblingSiteDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
		Long id = userDAO.getIdByEmail(req.getUserPrincipal().getName());
		List<GamblingSite> gamblingSiteList = gamblingSiteDAO.getAllSitesByUserId(id);
		return new MVCModel("/GamblingSites.jsp", gamblingSiteList, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("add site") != null) {
            return new MVCModel("/Redirect.jsp", "/gambling-site-add", null);
        }
        else {
            return new MVCModel("/GamblingSites.jsp", null, null);
        }
    }

}
