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
import java.sql.Blob;

@Component
@MultipartConfig //(location="C:\\Images", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class FileUpload {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    public Boolean uploadFile (HttpServletRequest req) throws IOException, ServletException{

        //final String destination = "C:\\Images\\";
        final Part filePart = req.getPart("file");
        if (!isImageFile(req, getInputFileName(filePart))){
            return false;
        }
        //final String fileName = userDAO.getIdByEmail(req.getUserPrincipal().getName()).toString() + ".png";

        //OutputStream out = null;
        InputStream fileContent = null;
        Blob blob = null;

        try {
            //out = new FileOutputStream(new File(destination + File.separator + fileName));

            fileContent = filePart.getInputStream();
            //byte[] bytes = getBytesFromInputStream(fileContent);


            try{
                blob = new javax.sql.rowset.serial.SerialBlob(ConvertInputStreamToByteArray.getBytesFromInputStream(fileContent));
            }
            catch (Exception e){
                e.printStackTrace();
            }


            Image image = new Image();
            image.setUserId(userDAO.getIdByEmail(req.getUserPrincipal().getName()));
            //image.setImage(blob);

            imageDAO.update(image);

            /*int read;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }*/
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            /*if (out != null) {
                out.close();
            }*/
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }

    private Boolean isImageFile (HttpServletRequest req, String inputFilename){
        String mime = req.getServletContext().getMimeType(inputFilename);
        return (mime.equalsIgnoreCase("image/gif"))|(mime.equalsIgnoreCase("image/png"))|(mime.equalsIgnoreCase("image/jpg"))|(mime.equalsIgnoreCase("image/jpeg"));
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
