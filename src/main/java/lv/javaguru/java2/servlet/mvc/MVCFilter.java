package lv.javaguru.java2.servlet.mvc;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MVCFilter implements Filter {

    private Map<String, MVCController> urlToControllerMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlToControllerMap = new HashMap<>();
        urlToControllerMap.put("/hello", new HelloWorldController());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();
        MVCController controller = urlToControllerMap.get(contextURI);
        MVCModel model = controller.processRequest(req);
        model.getJspName();

        req.setAttribute("data", model.getData());

        ServletContext context = req.getServletContext();
        RequestDispatcher requestDispatcher =
                context.getRequestDispatcher(model.getJspName());
        requestDispatcher.forward(req, resp);

    }

    @Override
    public void destroy() {

    }
}
