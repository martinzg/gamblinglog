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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessagesInboxController {

    private Logger logger = LoggerFactory.getLogger(MessagesInboxController.class);

    @Autowired
    private UserMessageDAO messageDAO;

    @RequestMapping(value = "messages", method = {RequestMethod.GET})
    public ModelAndView processGetAllRootRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        return new ModelAndView("MessagesInbox", getMessageListAndUnreadCount(request));
    }

    @RequestMapping(value = "messages/inbox", method = {RequestMethod.GET})
    public ModelAndView processGetAllRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        return new ModelAndView("MessagesInbox", getMessageListAndUnreadCount(request));
    }

    @RequestMapping(value = "messages/inbox/{id}", method = {RequestMethod.GET})
    public ModelAndView processGetOneRequest(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        UserMessage userMessage = messageDAO.getMessageById(id);
        if (userMessage != null){
            if (userMessage.getUserTo().equals(request.getUserPrincipal().getName())){
                userMessage.setReadState(true);
                messageDAO.update(userMessage);
                int count = messageDAO.getUnreadMessageCountByUserNameTo(request.getUserPrincipal().getName());

                Map<String, Object> myModel = new HashMap<>();
                myModel.put("count", count);
                myModel.put("messageOne", userMessage);

                return new ModelAndView("MessagesInboxOne", myModel);
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
        String message = "";
        String msgIds = request.getParameter("msgIds");

        if(msgIds.length() > 0){
            for (String item : msgIds.split(",")){
                messageDAO.delete(Long.parseLong(item));
            }
            message = "Message(s) successfully deleted!";
        }

        Map<String, Object> myModel = getMessageListAndUnreadCount(request);
        myModel.put("model", message);

        return new ModelAndView("MessagesInbox", myModel);
    }

    private Map<String, Object> getMessageListAndUnreadCount (HttpServletRequest request){
        int count = messageDAO.getUnreadMessageCountByUserNameTo(request.getUserPrincipal().getName());
        List<UserMessage> messageList = messageDAO.getMessagesByUserNameTo(request.getUserPrincipal().getName());

        Map<String, Object> myModel = new HashMap<>();
        myModel.put("count", count);
        myModel.put("messageList", messageList);

        return myModel;
    }

}
