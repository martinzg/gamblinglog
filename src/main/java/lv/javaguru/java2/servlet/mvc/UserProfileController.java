package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserProfileController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        Long id = null;
        try {
            id = userDAO.getIdByEmail(req.getUserPrincipal().getName());
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new MVCModel("/UserProfile.jsp", id, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("show sites") != null){
            return new MVCModel("/Redirect.jsp", "/java2/gamblingsites", null);
        }
        else {
            return null;
        }
    }

}
