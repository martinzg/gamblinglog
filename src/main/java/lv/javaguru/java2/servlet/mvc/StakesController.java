package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StakeDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Stake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tyoma17 on 18.04.2016.
 */
@Component
public class StakesController implements MVCController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private StakeDAO stakeDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest request) {

        try {
            Long id = Long.parseLong(userDAO.getIdByEmail(request.getUserPrincipal().getName()).toString());
            List<Stake> stakeList = stakeDAO.getAllStakes(id);
            return new MVCModel("/Stakes.jsp", stakeList, null);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest request) {
        if (request.getParameter("add stake") != null) {
            return new MVCModel("/Redirect.jsp", "/stake-add", null);
        } else {
            return new MVCModel("/Stakes.jsp", null, null);
        }
    }
}
