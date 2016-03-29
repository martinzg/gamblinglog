package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

public class HelloWorldController implements MVCController {
    @Override
    public MVCModel processRequest(HttpServletRequest req) {
        return new MVCModel("/helloWorld.jsp", "Hello from MVC");
    }
}
