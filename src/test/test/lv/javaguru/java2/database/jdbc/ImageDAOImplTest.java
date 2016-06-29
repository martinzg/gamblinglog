package test.lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ImageDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Image;
import lv.javaguru.java2.resources.ConvertInputStreamToByteArray;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.hibernate.JDBCException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.lv.javaguru.java2.servlet.mvc.UserCreator;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@WebAppConfiguration

public class ImageDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    private String email = "email@email.com";

    @Before
    public void init() throws JDBCException {
        userDAO.create(UserCreator.createUser("TestFirstName", "TestLastName", email, "Password"));
    }

    @After
    public void tearDown() throws JDBCException {
        userDAO.delete(userDAO.getIdByEmail(email));
    }

    @Test
    public void createAndGetImageByUserIdTest() throws Exception {
        imageDAO.delete(imageDAO.getImageByUserId(userDAO.getIdByEmail(email)).getId());
        Image image = createNewImage();
        imageDAO.create(image);

        assertEquals(image, imageDAO.getImageByUserId(userDAO.getIdByEmail(email)));
    }

    @Test
    public void getImageByUserIdNullTest() throws Exception {
        assertNull(imageDAO.getImageByUserId(1L));
    }

    @Test
    public void updateImageTest(){
        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));
        updateImageNameAndImageBlob(image);
        imageDAO.update(image);
        Image updatedImageFromDb = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));

        assertEquals(image, updatedImageFromDb);
    }

    @Test
    public void getAllImagesTest() {
        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));
        List<Image> imageList = imageDAO.getAll();

        assertTrue(imageList.size() >= 1);
        assertTrue(checkListOfImages(imageList, image));
    }

    @Test
    public void getImageByIdTest() throws SQLException{
        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));

        assertEquals(image, imageDAO.getById(image.getId()));
    }

    @Test
    public void getImageByIdNullTest() throws SQLException{
        assertNull(imageDAO.getById(1L));
    }

    @Test
    public void deleteImageTest(){
        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));
        imageDAO.delete(image.getId());

        assertNull(imageDAO.getImageByUserId(userDAO.getIdByEmail(email)));
    }

    private Blob getResourceImageAsBlob () throws IOException, SQLException {
        InputStream inputStream = ImageDAOImplTest.class.getResourceAsStream("/images/defaultGamblingLogImage.png");
        return new javax.sql.rowset.serial.SerialBlob(ConvertInputStreamToByteArray.getBytesFromInputStream(inputStream));
    }

    private Blob stringToSerialBlob(){
        String string = "TestBlob";
        Blob blob = null;
        try{
            blob = new SerialBlob(string.getBytes());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return blob;
    }

    private Image createNewImage() throws SQLException, IOException{
        Image image = new Image();
        image.setUserId(userDAO.getIdByEmail(email));
        image.setImageName("defaultGamblingLogImage.png");
        image.setImage(getResourceImageAsBlob());
        return image;
    }

    private void updateImageNameAndImageBlob(Image image){
        image.setImageName("newDefaultGamblingLogImage.png");
        image.setImage(stringToSerialBlob());
    }

    private Boolean checkListOfImages (List<Image> imageList, Image image){
        for (Image item : imageList){
            if (item.equals(image)){
                return true;
            }
        }
        return false;
    }

}