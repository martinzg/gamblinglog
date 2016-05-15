package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.resources.HashPassword;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "changepassword", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ChangePassword", "model", null);
    }

    @RequestMapping(value = "changepassword", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        try {
            if (session.getId().equals(request.getParameter("link"))){
                if (request.getParameter("password").equals(request.getParameter("confirm password"))){
                    Long userId = Long.parseLong(session.getAttribute("userId").toString());
                    User user = userDAO.getById(userId);
                    user.setPassword(HashPassword.hashPassword(request));
                    userDAO.update(user);
                    session.setAttribute("messageSuccess", "Your password has been successfully changed!");
                    return new ModelAndView("Redirect", "model", "/login");
                }
                else {
                    return new ModelAndView("ChangePassword", "model", "'New Password' and 'Confirm New Password' do not match!");
                }
            }
            else {
                return new ModelAndView("ChangePassword", "model", "Your password reset link has expired!");
            }
        }
        catch (JDBCException e) {
            throw new RuntimeException(e);
        }

    }

}
