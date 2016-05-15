package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.SendEmailFromGmail;
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
public class ForgotPasswordController {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "forgotpassword", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ForgotPassword", "model", null);
    }

    @RequestMapping(value = "forgotpassword", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        try {
            Long userId = userDAO.getIdByEmail(request.getParameter("email"));
            if (userId == null){
                return new ModelAndView("ForgotPassword", "model", "There is no user with such Email!");
            }
            else {
                session.setAttribute("userId", userId);
                sendEmail(request.getParameter("email"), "http://localhost:8080/changepassword?link=" + session.getId());
                return new ModelAndView("ForgotPassword", "model", "Password reset link has been sent to your email!");
            }
        }
        catch (JDBCException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendEmail(String email, String link){
        SendEmailFromGmail sendEmailFromGmail = new SendEmailFromGmail(email, link);
        try {
            sendEmailFromGmail.composeAndSendEmail();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
