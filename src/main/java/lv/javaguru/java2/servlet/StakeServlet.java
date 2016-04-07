package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.database.jdbc.StakeDAOImpl;
import lv.javaguru.java2.domain.Stake;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tyoma17 on 03.04.2016.
 */
public class StakeServlet extends HttpServlet {

    private static final long serialVersionUID = -6621753465033498446L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Stake stake = new Stake();
        StakeDAO stakeDAO = new StakeDAOImpl();

        try {
            getStakeAddData(stake, request);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            stakeDAO.create(stake);
            response.sendRedirect("/java2/stake-add?param=Success");
        } catch (DBException e) {
            e.printStackTrace();
            response.sendRedirect("/java2/stake-add?param=Failure");
        }
    }

    private void getStakeAddData(Stake stake, HttpServletRequest req) throws ParseException {
        String str = req.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        stake.setDate(date);
        stake.setUrl(req.getParameter("siteURL"));
        stake.setEvent(req.getParameter("event"));
        stake.setBetType(req.getParameter("betType"));
        stake.setBetAmount(Double.parseDouble(req.getParameter("betAmount")));
        stake.setCoefficient(Double.parseDouble(req.getParameter("coefficient")));
        stake.setResult(req.getParameter("result"));
        stake.setComment(req.getParameter("comment"));
        stake.setUserId(1002L);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/StakeAdd.jsp");
        requestDispatcher.forward(req, resp);
    }

}
