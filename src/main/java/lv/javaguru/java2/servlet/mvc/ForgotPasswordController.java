package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.SendEmailFromGmail;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

public class ForgotPasswordController implements MVCController{

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/ForgotPassword.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAOImpl();

        try {
            Long userId = userDAO.getIdByEmail(req.getParameter("email"));
            if (userId == null){
                return new MVCModel("/ForgotPassword.jsp", null, "There is no user with such Email!");
            }
            else {
                session.setAttribute("userId", userId);
                sendEmail(req.getParameter("email"), "http://localhost:8080/java2/changepassword?link=" + session.getId());
                return new MVCModel("/ForgotPassword.jsp", null, "Password reset link has been sent to your email!");
            }
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

    private String generatePassword(){
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i=0; i<20; i++){
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
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
