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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MVCFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(MVCFilter.class);

    private Map<String, MVCController> urlToControllerMap;

    private ApplicationContext springContext;

	private MVCController getBean(Class<?> clazz) {
		return (MVCController) springContext.getBean(clazz);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            springContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        }
        catch (BeansException e) {
            logger.error("Error! Spring context failure!");
        }

        urlToControllerMap = new HashMap<>();
        //urlToControllerMap.put("/hello", getBean(HelloWorldController.class));
        urlToControllerMap.put("/", getBean(LoginController.class));
        urlToControllerMap.put("/images", getBean(ImageController.class));
        //urlToControllerMap.put("/deleteuser", getBean(DeleteUserController.class));
        //urlToControllerMap.put("/login", getBean(LoginController.class));
        //urlToControllerMap.put("/logout", getBean(LogoutController.class));
        //urlToControllerMap.put("/userprofile", getBean(UserProfileController.class));
        //urlToControllerMap.put("/registration", getBean(UserRegistrationController.class));
        urlToControllerMap.put("/gamblingsites", getBean(GamblingSitesController.class));
		urlToControllerMap.put("/gambling-site-add", getBean(GamblingSiteAddController.class));
        urlToControllerMap.put("/stakes", getBean(StakesController.class));
        urlToControllerMap.put("/stake-add",getBean(StakeAddController.class));
        urlToControllerMap.put("/forgotpassword", getBean(ForgotPasswordController.class));
        urlToControllerMap.put("/changepassword", getBean(ChangePasswordController.class));
        urlToControllerMap.put("/addlandbasedgamblingevent", getBean(AddOfflineGamingEventController.class));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        resp.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        resp.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        resp.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

        String contextURI = req.getServletPath();
        String method = req.getMethod();

        MVCController controller;
        if(contextURI.contains("/images")){
            controller = urlToControllerMap.get("/images");
        }
        else {
            controller = urlToControllerMap.get(contextURI);
        }

        MVCModel model;
        if (method.equalsIgnoreCase("GET")){
            model = controller.processRequestGet(req, resp);
        }
        else {
            model = controller.processRequestPost(req);
        }

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