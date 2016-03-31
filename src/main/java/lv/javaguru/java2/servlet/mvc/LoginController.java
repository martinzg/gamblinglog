package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginController implements MVCController{

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/UserLogin.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {

        HttpSession session = req.getSession();

        String username = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
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
