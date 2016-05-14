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

@Controller
public class UserRegistrationController {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "registration", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("UserRegistration", "model", null);
    }

    @RequestMapping(value = "registration", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();

        try {
            if (userDAO.getIdByEmail(request.getParameter("email")) == null) {
                if (request.getParameter("password").equals(request.getParameter("confirm password"))){
                    getRegistrationFormInput(user, request);
                    userDAO.create(user);
                    request.getSession().setAttribute("messageSuccess", "User registered successfully!");
                    return new ModelAndView("Redirect", "model", "/login");
                }
                else {
                    return new ModelAndView("UserRegistration", "model", "'Password' and 'Confirm Password' do not match!");
                }
            }
            else{
                return new ModelAndView("UserRegistration", "model", "User with such email already exists!");
            }
        }
        catch (JDBCException e) {
            throw new RuntimeException(e);
        }

    }

    private void getRegistrationFormInput(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(HashPassword.hashPassword(req));
    }

}
