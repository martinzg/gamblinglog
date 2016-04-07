package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.database.jdbc.StakeDAOImpl;
import lv.javaguru.java2.domain.Stake;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tyoma17 on 07.04.2016.
 */
public class StakeAddController implements MVCController {
    @Override
    public MVCModel processRequestGet(HttpServletRequest request) {
        return new MVCModel("/StakeAdd.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest request) {
        Stake stake = new Stake();
        StakeDAO stakeDAO = new StakeDAOImpl();
        getStakeAddData(stake, request);
        try {
            stakeDAO.create(stake);
            return new MVCModel("/StakeAdd.jsp", null, "Success!");
        } catch (DBException e) {
            e.printStackTrace();
            return new MVCModel("/Redirect.jsp", null, "Failure!");
        }
    }

    private void getStakeAddData(Stake stake, HttpServletRequest request) {
        String str = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        stake.setDate(date);
        stake.setUrl(request.getParameter("siteURL"));
        stake.setEvent(request.getParameter("event"));
        stake.setBetType(request.getParameter("betType"));
        stake.setBetAmount(Double.parseDouble(request.getParameter("betAmount")));
        stake.setCoefficient(Double.parseDouble(request.getParameter("coefficient")));
        stake.setResult(request.getParameter("result"));
        stake.setComment(request.getParameter("comment"));
        stake.setUserId(Long.parseLong(request.getSession().getAttribute("userId").toString()));
    }

}
