package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

		req.setAttribute("message", message);
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserLogin.jsp");
		requestDispatcher.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserLogin.jsp");
		requestDispatcher.forward(req, resp);

	}

}
