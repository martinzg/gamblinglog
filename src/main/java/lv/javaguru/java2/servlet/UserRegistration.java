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
        getRegistrationData(user, req);

        UserDAO userDAO = new UserDAOImpl();

        try {
            if (userDAO.getIdByEmail(req.getParameter("email")) == null) {
                if (req.getParameter("password").equals(req.getParameter("confirm password"))){
                    userDAO.create(user);
                    resp.sendRedirect("/java2/login?param=User registered successfully!");
                }
                else {
                    redirect(resp, "'Password' and 'Confirm Password' do not match!");
                }
            }
            else{
                redirect(resp, "User with such email already exists!");
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
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/UserRegistration.jsp");
        requestDispatcher.forward(req, resp);
	}

    private void getRegistrationData(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
    }

    private void redirect(HttpServletResponse resp, String message) throws ServletException, IOException {
        resp.sendRedirect("/java2/registration?param=" + message);
    }

}
