package lv.javaguru.java2.resources;

import lv.javaguru.java2.database.ImageDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

@Component
@MultipartConfig
public class FileUpload {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    public Boolean uploadFile (HttpServletRequest req) throws IOException, ServletException{

        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(req.getUserPrincipal().getName()));

        final Part filePart = req.getPart("file");
        if (!isImageFile(req, getInputFileName(filePart))){
            return false;
        }

        InputStream inputStream = null;

        try {
            inputStream = filePart.getInputStream();
            image.setImageName(getInputFileName(filePart));
            image.setImage(new javax.sql.rowset.serial.SerialBlob(ConvertInputStreamToByteArray.getBytesFromInputStream(inputStream)));
            imageDAO.update(image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    private Boolean isImageFile (HttpServletRequest req, String inputFilename){
        String mime = req.getServletContext().getMimeType(inputFilename);
        return (mime.equalsIgnoreCase("image/bmp"))|
                (mime.equalsIgnoreCase("image/gif"))|
                (mime.equalsIgnoreCase("image/png"))|
                (mime.equalsIgnoreCase("image/jpg"))|
                (mime.equalsIgnoreCase("image/jpeg"));
    }

    private String getInputFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
