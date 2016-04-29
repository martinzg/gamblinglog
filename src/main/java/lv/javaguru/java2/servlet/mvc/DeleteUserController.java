package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeleteUserController implements MVCController{

    @Autowired
    UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
        String userEmail = req.getUserPrincipal().getName();
        req.getSession().invalidate();
        userDAO.delete(userDAO.getIdByEmail(userEmail));
        return new MVCModel("/Redirect.jsp", "/login", null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        String userEmail = req.getUserPrincipal().getName();
        req.getSession().invalidate();
        userDAO.delete(userDAO.getIdByEmail(userEmail));
        return new MVCModel("/Redirect.jsp", "/login", null);
    }

}
