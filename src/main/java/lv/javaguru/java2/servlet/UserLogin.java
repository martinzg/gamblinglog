package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String username = req.getParameter("email");
		String password = req.getParameter("password");

		UserDAO userDAO = new UserDAOImpl();

		String message = "Login Success!";
		Long userId;

		try {
			userId = userDAO.getIdByEmail(username);
			if (userId == null){
				message = "Invalid Email!";
			}
			else {
				if (userDAO.getById(userId).getPassword().equals(password)){
					message = "Login Success!";
				}
				else {
					message = "Invalid Password!";
				}
			}
		}
		catch (DBException e) {
			e.printStackTrace();
		}

		out.println("<h1>" + "User login:" + "</h1>");
		out.println("<form method=\"post\">\n" +
				"  Email:<br>\n" +
				"  <input type=\"email\" name=\"email\" required>\n" +
				"  <br>\n" +
				"  Password:<br>\n" +
				"  <input type=\"password\" name=\"password\" required>\n" +
				"  <br><br>\n" +
				"  <input type=\"submit\" name=\"submit\">\n" +
				"</form>");
		out.println("<h4 style=\"color:red;\">" + "\n" + message + "</h4>");
		out.println("<a href=\"/java2/registration\">" + "Register" + "</a>");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<h1>" + "User login:" + "</h1>");
		out.println("<form method=\"post\">\n" +
				"  Email:<br>\n" +
				"  <input type=\"email\" name=\"email\" required>\n" +
				"  <br>\n" +
				"  Password:<br>\n" +
				"  <input type=\"password\" name=\"password\" required>\n" +
				"  <br><br>\n" +
				"  <input type=\"submit\" name=\"submit\">\n" +
				"</form>");
		out.println("<a href=\"/java2/registration\">" + "Register" + "</a>");

	}

}
