package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        return new MVCModel("/helloWorld.jsp", null,"Hello from MVC");
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        return new MVCModel("/helloWorld.jsp", null,"Hello from MVC");
    }

}
