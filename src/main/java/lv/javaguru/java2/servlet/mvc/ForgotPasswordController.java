package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.SendEmailFromGmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class ForgotPasswordController implements MVCController{

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/ForgotPassword.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        HttpSession session = req.getSession();

        try {
            Long userId = userDAO.getIdByEmail(req.getParameter("email"));
            if (userId == null){
                return new MVCModel("/ForgotPassword.jsp", null, "There is no user with such Email!");
            }
            else {
                session.setAttribute("userId", userId);
                sendEmail(req.getParameter("email"), "http://localhost:8080/changepassword?link=" + session.getId());
                return new MVCModel("/ForgotPassword.jsp", null, "Password reset link has been sent to your email!");
            }
        }
        catch (DBException e) {
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
