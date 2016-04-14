package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogoutController implements MVCController{

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        req.getSession().invalidate();
        return new MVCModel("/Redirect.jsp", "/login", null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        req.getSession().invalidate();
        return new MVCModel("/Redirect.jsp", "/login", null);
    }

}
