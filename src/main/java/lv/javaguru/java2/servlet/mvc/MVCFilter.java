package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MVCFilter implements Filter {

    private Map<String, MVCController> urlToControllerMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlToControllerMap = new HashMap<>();
        urlToControllerMap.put("/hello", new HelloWorldController());
        urlToControllerMap.put("/login", new LoginController());
        urlToControllerMap.put("/userprofile", new UserProfileController());
        urlToControllerMap.put("/registration", new UserRegistrationController());
        urlToControllerMap.put("/gamblingsites", new GamblingSitesController());
		urlToControllerMap.put("/gambling-site-add", new GamblingSiteAddController());
        urlToControllerMap.put("/stake-add", new StakeAddController());
        urlToControllerMap.put("/forgotpassword", new ForgotPasswordController());
        urlToControllerMap.put("/changepassword", new ChangePasswordController());
        urlToControllerMap.put("/addlandbasedgamblingevent", new AddOfflineGamingEventController());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();
        String method = req.getMethod();

        MVCController controller = urlToControllerMap.get(contextURI);
        MVCModel model;

        if (method.equalsIgnoreCase("GET")){
            model = controller.processRequestGet(req);
        }
        else {
            model = controller.processRequestPost(req);
        }

        model.getJspName();

        req.setAttribute("data", model.getData());
        req.setAttribute("message", model.getMessage());

        ServletContext context = req.getServletContext();
        RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
        requestDispatcher.forward(req, resp);

    }

    @Override
    public void destroy() {

    }
}