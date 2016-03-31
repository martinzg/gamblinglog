package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.jdbc.GamblingSiteDAOImpl;
import lv.javaguru.java2.domain.GamblingSite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GamblingSitesController implements MVCController {

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Long id = Long.parseLong(session.getAttribute("userId").toString());
        GamblingSiteDAO gamblingSiteDAO = new GamblingSiteDAOImpl();

        try {
            List<GamblingSite> gamblingSiteList = gamblingSiteDAO.getAllSitesByUserId(id);
            return new MVCModel("/GamblingSites.jsp", gamblingSiteList, null);
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("add site") != null) {
            return new MVCModel("/Redirect.jsp", "/java2/gambling-site-add", null);
        }
        else {
            return new MVCModel("/GamblingSites.jsp", null, null);
        }
    }

}
