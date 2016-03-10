package lv.javaguru.java2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParameterProcessingServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req,
	                     HttpServletResponse resp) throws ServletException, IOException {

		// Set response content type
		resp.setContentType("text/html");

		// Get parameter from request
		String paramValue = req.getParameter("param1");

		// Prepare output html
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + "Hello World from Java!" + "</h1>");
		out.println("Param 1 = " + paramValue);
	}

}
