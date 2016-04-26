package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.resources.HashPassword;
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
            User user = userDAO.getById(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
            return new MVCModel("/UserProfile.jsp", user, null);
        }
        if (req.getParameter("update") != null){
            User user = userDAO.getById(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
            getUserProfileFormInput(user, req);
            userDAO.update(user);
            return new MVCModel("/UserProfile.jsp", null, "User Profile updated successfully!");
        }
        else {
            return new MVCModel("/Redirect.jsp", "/userprofile", null);
        }
    }

    private void getUserProfileFormInput(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        if (!req.getParameter("password").equals(user.getPassword())){
            user.setPassword(HashPassword.hashPassword(req));
        }
    }

}
