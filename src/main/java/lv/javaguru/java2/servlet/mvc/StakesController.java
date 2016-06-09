package lv.javaguru.java2.servlet.mvc;


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
import java.util.List;

/**
 * Created by tyoma17 on 18.04.2016.
 */
@Controller
public class StakesController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StakeDAO stakeDAO;

    @RequestMapping(value = "stakes", method = {RequestMethod.GET})
    public ModelAndView processRequestGet(HttpServletRequest request, HttpServletResponse response) {
        Long id = userDAO.getIdByEmail(request.getUserPrincipal().getName());
        List<Stake> stakeList = stakeDAO.getAllStakes(id);
        return new ModelAndView("Stakes", "stakeList", stakeList);
    }

    @RequestMapping(value = "stakes", method = {RequestMethod.POST})
    public ModelAndView processRequestPost(HttpServletRequest request) {
        if (request.getParameter("add stake") != null) {
            return new ModelAndView("Redirect", "model", "/stake-add");
        }
        else {
            return new ModelAndView("Redirect", "model", "/stakes");
        }
    }
}
