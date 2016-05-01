package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.GamblingEventDAOImpl;
import lv.javaguru.java2.database.jdbc.LandBasedCasinoDAOImpl;
import lv.javaguru.java2.domain.OfflineGamblingEvent;
import lv.javaguru.java2.resources.OfflineGamblingEventData;
import lv.javaguru.java2.utils.htmlSelectParser;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.sql.Date.*;

@Component
public class AddOfflineGamingEventController implements MVCController {

    private Logger logger = LoggerFactory.getLogger(AddOfflineGamingEventController.class);

    @Autowired
    private LandBasedCasinoDAOImpl landBasedCasinoDAO;
    @Autowired
    private GamblingEventDAOImpl gamblingEventDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private htmlSelectParser selectParser;

    private OfflineGamblingEventData addDefaultInformation(OfflineGamblingEventData data) {
        data.setLandBasedCasinoList(landBasedCasinoDAO.getAll());
        data.setGamblingTypeList(gamblingEventDAO.getAll());
        return data;
    }

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
        OfflineGamblingEventData data = new OfflineGamblingEventData();
        addDefaultInformation(data);
        return new MVCModel("/AddOfflineGamblingEvent.jsp", data, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        OfflineGamblingEvent event = new OfflineGamblingEvent();
        OfflineGamblingEventData data = new OfflineGamblingEventData();
        event.setPlaceId(Long.parseLong(req.getParameter("casino")));
        event.setGameId(selectParser.parse(req, "type"));
        event.setDate(valueOf(req.getParameter("date")));
        try {
            event.setUserId(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
        } catch (JDBCException e) {
            e.printStackTrace();
            logger.error("Error while getting user ID");
        }
        event.setComment(req.getParameter("comment"));
        data.setMessage("Event added, add more?");
        addDefaultInformation(data);
        return new MVCModel("/AddOfflineGamblingEvent.jsp", data, null);
    }
}
