package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;

@Component
public class UserRegistrationController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/UserRegistration.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        User user = new User();

        try {
            if (userDAO.getIdByEmail(req.getParameter("email")) == null) {
                if (req.getParameter("password").equals(req.getParameter("confirm password"))){
                    getRegistrationFormInput(user, req);
                    userDAO.create(user);
                    return new MVCModel("/Redirect.jsp", "/login", "User registered successfully!");
                }
                else {
                    return new MVCModel("/UserRegistration.jsp", null, "'Password' and 'Confirm Password' do not match!");
                }
            }
            else{
                return new MVCModel("/UserRegistration.jsp", null, "User with such email already exists!");
            }
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

    private void getRegistrationFormInput(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(hashPassword(req));
    }

    private String hashPassword (HttpServletRequest req) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordHashed = messageDigest.digest(req.getParameter("password").getBytes("UTF-8"));

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < passwordHashed.length; i++) {
                sb.append(Integer.toHexString((passwordHashed[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
