package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserProfileController implements MVCController {

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/UserProfile.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("show sites") != null){
            return new MVCModel("/Redirect.jsp", "/java2/gamblingsites", null);
        }
        else {
            return null;
        }
    }

}
