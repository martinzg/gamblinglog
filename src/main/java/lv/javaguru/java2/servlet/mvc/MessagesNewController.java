package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.resources.SetHeaderNoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MessagesNewController {

    private Logger logger = LoggerFactory.getLogger(MessagesNewController.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserMessageDAO messageDAO;

    public void setUserDAO (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void setUserMessageDAO (UserMessageDAO userMessageDAO){
        this.messageDAO = userMessageDAO;
    }

    @RequestMapping(value = "messages/newmessage", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        int count = messageDAO.getUnreadMessageCountByUserNameTo(request.getUserPrincipal().getName());
        return new ModelAndView("MessagesNew", "count", count);
    }

    @RequestMapping(value = "messages/newmessage", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        String message = "";
        if (request.getParameter("send") != null){
            String recipient = request.getParameter("recipient");
            String comment = request.getParameter("comment");
            if (userDAO.getIdByEmail(recipient) != null && !recipient.equals(request.getUserPrincipal().getName())){
                if (comment.length() <= 1000){
                    messageDAO.create(createMessageFromInputs(request));
                    message = "Message sent!";
                }
                else {
                    message = "Message too long! Message can't be longer that 1000 chars.";
                }
            }
            else {
                message = "Wrong Recipient!";
            }
        }

        int count = messageDAO.getUnreadMessageCountByUserNameTo(request.getUserPrincipal().getName());
        Map<String, Object> myModel = new HashMap<>();
        myModel.put("count", count);
        myModel.put("model", message);

        return new ModelAndView("MessagesNew", myModel);
    }

    private UserMessage createMessageFromInputs (HttpServletRequest request){
        UserMessage userMessage = new UserMessage();
        userMessage.setUserFrom(request.getUserPrincipal().getName());
        userMessage.setUserTo(request.getParameter("recipient"));
        userMessage.setDateTime(new Date());
        userMessage.setMessage(request.getParameter("comment"));
        userMessage.setReadState(false);
        return userMessage;
    }

}
