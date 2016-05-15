package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.ImageDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Image;
import lv.javaguru.java2.resources.ConvertInputStreamToByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@Controller
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    @RequestMapping(value = "image", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {

        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(req.getUserPrincipal().getName()));

        ServletContext context= req.getServletContext();
        String filename = image.getImageName();
        String mime = context.getMimeType(filename);
        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ModelAndView("Image", "image", null);
        }
        resp.setContentType(mime);

        byte[] bytesIn;
        InputStream in;
        OutputStream out = null;

        try{
            bytesIn = ConvertInputStreamToByteArray.getBytesFromInputStream(image.getImage().getBinaryStream());

            in = cropIfDefaultImage(bytesIn, filename);
            out = resp.getOutputStream();

            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
        }
        catch (Exception e){
            logger.error("Error! Image output failed!");
            e.printStackTrace();
        }

        return new ModelAndView("Image", "image", out);

    }

    private InputStream cropIfDefaultImage (byte[] bytes, String filename){
        if (filename.equalsIgnoreCase("defaultGamblingLogImage.png")){
            return new ByteArrayInputStream(Arrays.copyOfRange(bytes, 147, bytes.length));
        }
        return new ByteArrayInputStream(bytes);
    }

}
