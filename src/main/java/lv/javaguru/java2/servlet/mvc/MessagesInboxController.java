package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.resources.SetHeaderNoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MessagesInboxController {

    private Logger logger = LoggerFactory.getLogger(MessagesInboxController.class);

    @Autowired
    private UserMessageDAO messageDAO;

    @RequestMapping(value = "messages", method = {RequestMethod.GET})
    public ModelAndView processGetAllRootRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        List<UserMessage> messageList = messageDAO.getMessagesByUserNameTo(request.getUserPrincipal().getName());
        return new ModelAndView("MessagesInbox", "messageList", messageList);
    }

    @RequestMapping(value = "messages/inbox", method = {RequestMethod.GET})
    public ModelAndView processGetAllRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        List<UserMessage> messageList = messageDAO.getMessagesByUserNameTo(request.getUserPrincipal().getName());
        return new ModelAndView("MessagesInbox", "messageList", messageList);
    }

    @RequestMapping(value = "messages/inbox/{id}", method = {RequestMethod.GET})
    public ModelAndView processGetOneRequest(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        UserMessage userMessage = messageDAO.getMessageById(id);
        if (userMessage != null){
            if (userMessage.getUserTo().equals(request.getUserPrincipal().getName())){
                return new ModelAndView("MessagesInboxOne", "messageOne", userMessage);
            }
            else {
                return new ModelAndView("Redirect", "model", "/messages/inbox");
            }
        }
        else {
            return new ModelAndView("Redirect", "model", "/messages/inbox");
        }
    }

    @RequestMapping(value = "messages/inbox", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        return new ModelAndView("MessagesInbox", "model", null);
    }

}
