package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.resources.SetHeaderNoCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView processGetRootRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        return new ModelAndView("Redirect", "model", "/userprofile");
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        return new ModelAndView("Redirect", "model", "/userprofile");
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public ModelAndView processPostRootRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        return new ModelAndView("Redirect", "model", "/userprofile");
    }

    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest req, HttpServletResponse resp) {
        SetHeaderNoCache.setNoCache(resp);
        return new ModelAndView("Redirect", "model", "/userprofile");
    }

}
