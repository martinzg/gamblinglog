package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.jdbc.LandBasedCasinoDAOImpl;
import lv.javaguru.java2.resources.OfflineGamblingEventData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AddOfflineGamingEventController implements MVCController {

    @Override
    public MVCModel processRequestGet(HttpServletRequest req) {
        OfflineGamblingEventData date = new OfflineGamblingEventData();
        date.setLandBasedCasinoList(new LandBasedCasinoDAOImpl().getAll());
        return new MVCModel("/AddOfflineGamblingEvent.jsp", date, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        return null; //TODO: process this part
    }
}
