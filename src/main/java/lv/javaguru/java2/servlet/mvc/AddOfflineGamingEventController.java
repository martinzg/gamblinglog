package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.jdbc.GamblingEventDAOImpl;
import lv.javaguru.java2.database.jdbc.LandBasedCasinoDAOImpl;
import lv.javaguru.java2.resources.OfflineGamblingEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AddOfflineGamingEventController implements MVCController {

    @Autowired
    private LandBasedCasinoDAOImpl landBasedCasinoDAO;
    @Autowired
    private GamblingEventDAOImpl gamblingEventDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        OfflineGamblingEventData data = new OfflineGamblingEventData();
        data.setLandBasedCasinoList(landBasedCasinoDAO.getAll());
        data.setGamblingTypeList(gamblingEventDAO.getAll());
        return new MVCModel("/AddOfflineGamblingEvent.jsp", data, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        return null; //TODO: process this part
    }
}
