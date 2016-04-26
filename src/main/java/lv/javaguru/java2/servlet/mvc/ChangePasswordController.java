package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.resources.HashPassword;
import org.hibernate.JDBCException;
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
                    user.setPassword(HashPassword.hashPassword(req));
                    userDAO.update(user);
                    req.getSession().setAttribute("messageSuccess", "Your password has been successfully changed!");
                    return new MVCModel("/Redirect.jsp", "/login", null);
                }
                else {
                    return new MVCModel("/ChangePassword.jsp", null, "'New Password' and 'Confirm New Password' do not match!");
                }
            }
            else {
                return new MVCModel("/ChangePassword.jsp", null, "Your password reset link has expired!");
            }
        }
        catch (JDBCException e) {
            throw new RuntimeException(e);
        }

    }

}
