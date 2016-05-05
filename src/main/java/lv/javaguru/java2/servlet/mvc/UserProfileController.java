package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.resources.FileUpload;
import lv.javaguru.java2.resources.HashPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component
public class UserProfileController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FileUpload fileUpload;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {
        return new MVCModel("/UserProfile.jsp", null, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        if (req.getParameter("edit") != null){
            User user = userDAO.getById(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
            return new MVCModel("/UserProfile.jsp", user, null);
        }
        if (req.getParameter("update") != null){
            User user = userDAO.getById(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
            getUserProfileFormInput(user, req);
            userDAO.update(user);
            return new MVCModel("/UserProfile.jsp", null, "User Profile updated successfully!");
        }
        if (req.getParameter("upload") != null){
            return verifyAndUploadFile(req);
        }
        else {
            return new MVCModel("/UserProfile.jsp", null, null);
        }
    }

    private void getUserProfileFormInput(User user, HttpServletRequest req){
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        if (!req.getParameter("password").equals(user.getPassword())){
            user.setPassword(HashPassword.hashPassword(req));
        }
    }

    private void setUserPictureTrue (HttpServletRequest req){
        User user = userDAO.getById(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
        user.setImage(true);
        userDAO.update(user);
    }

    private MVCModel verifyAndUploadFile (HttpServletRequest req){
        String message;
        try{
            Part part = req.getPart("file");
            if (part.getSize() > 0){
                if (fileUpload.uploadFile(req)) {
                    message = "Image uploaded successfully!";
                    setUserPictureTrue(req);
                }
                else {
                    message = "File is not an image (.jpg, .png, .gif)!";
                }
            }
            else {
                message = "Please select file for upload!";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            message = "Image upload failed!";
        }
        return new MVCModel("/UserProfile.jsp", null, message);
    }

}
