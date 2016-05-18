package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.resources.SetHeaderNoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteUserController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "deleteuser", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        String userEmail = req.getUserPrincipal().getName();
        req.getSession().invalidate();
        userDAO.delete(userDAO.getIdByEmail(userEmail));
        return new ModelAndView("Redirect", "model", "/login");
    }

}
