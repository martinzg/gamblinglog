package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.SendEmailFromGmail;
import lv.javaguru.java2.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class ForgotPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		UserDAO userDAO = new UserDAOImpl();

		try {
            Long userId = userDAO.getIdByEmail(req.getParameter("email"));
			if (userId == null){
                resp.sendRedirect("/java2/forgotpassword?param=There is no user with such Email!");
			}
            else {
                User user = userDAO.getById(userId);
                user.setPassword(generatePassword());
                userDAO.update(user);
                sendEmail(user.getEmail(), user.getPassword());
                resp.sendRedirect("/java2/login?param=New password has been sent to your email!");
            }
		}
		catch (DBException e){
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/ForgotPassword.jsp");
        requestDispatcher.forward(req, resp);
	}

    private String generatePassword(){
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i=0; i<10; i++){
            password.append(random.nextInt(9 + 1));
        }
        return password.toString();
    }

    private void sendEmail(String email, String password){
        SendEmailFromGmail sendEmailFromGmail = new SendEmailFromGmail(email, password);
        try {
            sendEmailFromGmail.composeAndSendEmail();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
