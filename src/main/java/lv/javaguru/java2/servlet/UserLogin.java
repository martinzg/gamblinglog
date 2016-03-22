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

public class UserLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		String username = req.getParameter("email");
		String password = req.getParameter("password");

		UserDAO userDAO = new UserDAOImpl();
		Long userId;

		try {
			userId = userDAO.getIdByEmail(username);
			if (userId == null){
				redirect(resp, "Invalid Email!");
			}
			else {
				if (userDAO.getById(userId).getPassword().equals(password)){
					//here must be redirect to user profile
					redirect(resp, "Login Success!");
				}
				else {
					redirect(resp, "Invalid Password!");
				}
			}
		}
		catch (DBException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserLogin.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void redirect(HttpServletResponse resp, String message) throws ServletException, IOException {
		resp.sendRedirect("/java2/login?param=" + message);
	}

}
