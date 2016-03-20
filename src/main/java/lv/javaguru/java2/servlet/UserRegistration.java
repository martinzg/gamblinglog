package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

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
                    return;
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

        req.setAttribute("message", message);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserRegistration.jsp");
        requestDispatcher.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserRegistration.jsp");
        requestDispatcher.forward(req, resp);

	}

}
