package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LoginController implements MVCController{

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/UserLogin.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        HttpSession session = req.getSession();

        String username = req.getParameter("email");
        String password = req.getParameter("password");

        Long userId;

        try {
            userId = userDAO.getIdByEmail(username);
            if (userId == null){
                return new MVCModel("/UserLogin.jsp", null,"Invalid Email!");
            }
            else {
                if (userDAO.getById(userId).getPassword().equals(password)){
                    session.setAttribute("userId", userId);
                    return new MVCModel("/Redirect.jsp", "/java2/userprofile", null);
                }
                else {
                    return new MVCModel("/UserLogin.jsp", null, "Invalid Password!");
                }
            }
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

}
