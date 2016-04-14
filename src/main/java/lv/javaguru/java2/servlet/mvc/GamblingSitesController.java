package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GamblingSiteDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.GamblingSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component
public class GamblingSitesController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GamblingSiteDAO gamblingSiteDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        try {
            Long id = userDAO.getIdByEmail(req.getUserPrincipal().getName());
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
            return new MVCModel("/Redirect.jsp", "/gambling-site-add", null);
        }
        else {
            return new MVCModel("/GamblingSites.jsp", null, null);
        }
    }

}
