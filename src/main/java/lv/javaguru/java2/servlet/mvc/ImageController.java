package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequestGet(HttpServletRequest req, HttpServletResponse resp) {

        ServletContext context = req.getServletContext();
        // Get the absolute path of the image
        String filename = "C:\\Images\\" + checkUserImage(req) + ".png";
        // retrieve mimeType dynamically
        String mime = context.getMimeType(filename);
        if (mime == null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new MVCModel("/Image.jsp", null, null);
        }

        resp.setContentType(mime);
        File file = new File(filename);
        resp.setContentLength((int)file.length());

        FileInputStream in;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = resp.getOutputStream();
            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return new MVCModel("/Image.jsp", out, null);
    }

    @Override
    public MVCModel processRequestPost(HttpServletRequest req) {
        return new MVCModel("/Image.jsp", null, null);
    }

    private Long checkUserImage (HttpServletRequest req){
        Long userId = userDAO.getIdByEmail(req.getUserPrincipal().getName());
        return userDAO.getById(userId).getImage() ? userId : 1000L;
    }

}
