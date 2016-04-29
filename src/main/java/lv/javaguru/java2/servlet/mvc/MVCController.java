package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MVCController {

    MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp);
    MVCModel processRequestPost(HttpServletRequest req);

}

