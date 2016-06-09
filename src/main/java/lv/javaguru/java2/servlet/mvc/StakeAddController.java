package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Stake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tyoma17 on 07.04.2016.
 */
@Controller
public class StakeAddController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StakeDAO stakeDAO;

    @RequestMapping(value = "stake-add", method = {RequestMethod.GET})
    public ModelAndView processRequestGet(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("StakeAdd", "model", null);
    }

    @RequestMapping(value = "stake-add", method = {RequestMethod.POST})
    public ModelAndView processRequestPost(HttpServletRequest request) {
        if (request.getParameter("back") != null) {
            return new ModelAndView("Redirect", "model", "/stakes");
        }

        try {
            Stake stake = new Stake();
            getStakeAddData(stake, request);
            stakeDAO.create(stake);
            return new ModelAndView("StakeAdd", "message", "Success!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("StakeAdd", "message", "Failure!");
        }
    }

    private void getStakeAddData(Stake stake, HttpServletRequest request) throws DBException {
        String str = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        stake.setUserId(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
    }

}
