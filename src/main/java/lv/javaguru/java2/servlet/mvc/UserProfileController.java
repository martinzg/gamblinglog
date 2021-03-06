package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.resources.FileUpload;
import lv.javaguru.java2.resources.HashPassword;

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
import javax.servlet.http.Part;

@Controller
public class UserProfileController {

    private Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FileUpload fileUpload;

    @RequestMapping(value = "userprofile", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        return new ModelAndView("UserProfile", "model", null);
    }

    @RequestMapping(value = "userprofile", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        SetHeaderNoCache.setNoCache(response);
        if (request.getParameter("edit") != null){
            User user = userDAO.getById(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
            return new ModelAndView("UserProfile", "user", user);
        }
        if (request.getParameter("update") != null){
            User user = userDAO.getById(userDAO.getIdByEmail(request.getUserPrincipal().getName()));
            getUserProfileFormInput(user, request);
            userDAO.update(user);
            return new ModelAndView("UserProfile", "model", "User Profile updated successfully!");
        }
        if (request.getParameter("upload") != null){
            return verifyAndUploadFile(request);
        }
        else {
            return new ModelAndView("UserProfile", "model", null);
        }
    }

    private void getUserProfileFormInput(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        if (!req.getParameter("password").equals(user.getPassword())){
            user.setPassword(HashPassword.hashPassword(req));
        }
    }

    private ModelAndView verifyAndUploadFile (HttpServletRequest req){
        String message;
        try{
            Part part = req.getPart("file");
            if (part.getSize() > 0 && part.getSize() < 4000000){
                if (fileUpload.uploadFile(req)) {
                    message = "Image uploaded successfully!";
                }
                else {
                    message = "File is not an image (.jpg, .png, .gif, .bmp)!";
                }
            }
            else {
                if (part.getSize() <= 0){
                    message = "Please select file for upload!";
                }
                else {
                    message = "File is to large! Max file size = 4 Mb";
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Error! Image upload failed!");
            message = "Image upload failed!";
        }
        return new ModelAndView("UserProfile", "model", message);
    }

}
