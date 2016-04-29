package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginController implements MVCController{

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
        return new MVCModel("/Redirect.jsp", "/userprofile", null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        return new MVCModel("/Redirect.jsp", "/userprofile", null);
    }

}
