package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class ChangePasswordController implements MVCController{

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/ChangePassword.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        HttpSession session = req.getSession();

        try {
            if (session.getId().equals(req.getParameter("link"))){
                if (req.getParameter("password").equals(req.getParameter("confirm password"))){

                    Long userId = Long.parseLong(session.getAttribute("userId").toString());
                    User user = userDAO.getById(userId);
                    user.setPassword(req.getParameter("password"));
                    userDAO.update(user);

                    return new MVCModel("/Redirect.jsp", "/java2/login", "Your password has been successfully changed!");
                }
                else {
                    return new MVCModel("/ChangePassword.jsp", null, "'New Password' and 'Confirm New Password' do not match!");
                }
            }
            else {
                return new MVCModel("/ChangePassword.jsp", null, "Your password reset link has expired!");
            }
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

}
