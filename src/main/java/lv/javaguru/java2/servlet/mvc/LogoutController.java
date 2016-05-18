package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.resources.SetHeaderNoCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        req.getSession().invalidate();
        return new ModelAndView("Redirect", "model", "/login");
    }

}
