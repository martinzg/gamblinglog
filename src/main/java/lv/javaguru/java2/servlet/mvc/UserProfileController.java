package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserProfileController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/UserProfile.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("edit") != null){
            return new MVCModel("/Redirect.jsp", "/userprofile?param=1", null);
        }
        else {
            return new MVCModel("/Redirect.jsp", "/userprofile", null);
        }
    }

}
