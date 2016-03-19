package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserRegistration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User user = new User();
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));

        UserDAO userDAO = new UserDAOImpl();

        String message = "User registered!";

        try {
            if (userDAO.getIdByEmail(req.getParameter("email")) == null) {
                if (req.getParameter("password").equals(req.getParameter("confirm password"))){
                    userDAO.create(user);
                    resp.sendRedirect("/java2/login");
                }
                else {
                    message = "'Password' and 'Confirm Password' do not match!";
                }
            }
            else{
                message = "User with such email already exists!";
            }
        }
        catch (DBException e) {
            e.printStackTrace();
        }

        out.println("<h1>" + "New user registration:" + "</h1>");
        out.println("<form method=\"post\">\n" +
                "  First name:<br>\n" +
                "  <input type=\"text\" name=\"firstname\" required>\n" +
                "  <br>\n" +
                "  Last name:<br>\n" +
                "  <input type=\"text\" name=\"lastname\" required>\n" +
                "  <br>\n" +
                "  Email:<br>\n" +
                "  <input type=\"email\" name=\"email\" required>\n" +
                "  <br>\n" +
                "  Password:<br>\n" +
                "  <input type=\"password\" name=\"password\" required>\n" +
                "  <br>\n" +
                "  Confirm Password:<br>\n" +
                "  <input type=\"password\" name=\"confirm password\" required>\n" +
                "  <br><br>\n" +
                "  <input type=\"submit\" name=\"submit\">\n" +
                "</form>");
        out.println("<h4 style=\"color:red;\">" + "\n" + message + "</h4>");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

		out.println("<h1>" + "New user registration:" + "</h1>");
		out.println("<form method=\"post\">\n" +
				"  First name:<br>\n" +
				"  <input type=\"text\" name=\"firstname\" required>\n" +
				"  <br>\n" +
				"  Last name:<br>\n" +
				"  <input type=\"text\" name=\"lastname\" required>\n" +
				"  <br>\n" +
				"  Email:<br>\n" +
				"  <input type=\"email\" name=\"email\" required>\n" +
				"  <br>\n" +
				"  Password:<br>\n" +
				"  <input type=\"password\" name=\"password\" required>\n" +
				"  <br>\n" +
				"  Confirm Password:<br>\n" +
				"  <input type=\"password\" name=\"confirm password\" required>\n" +
				"  <br><br>\n" +
				"  <input type=\"submit\" name=\"submit\">\n" +
				"</form>");
	}

}
