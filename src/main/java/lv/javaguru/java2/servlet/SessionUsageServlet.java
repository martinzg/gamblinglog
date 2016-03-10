package lv.javaguru.java2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionUsageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req,
	                     HttpServletResponse resp) throws ServletException, IOException {

		// Set response content type
		resp.setContentType("text/html");

		HttpSession session = req.getSession();
		Long counter = (Long)session.getAttribute("key");
		if(counter == null) {
			counter = 0L;
		}
		counter++;
		session.setAttribute("key", counter);

		// Actual logic goes here.
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + "Hello World from Java!" + "</h1>");
		out.println("Counrer = " + session.getAttribute("key"));
	}

}
