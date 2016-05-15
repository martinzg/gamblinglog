package test.lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ImageDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Image;
import lv.javaguru.java2.domain.User;
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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

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
        User user = UserCreator.createUser("firstName", "lastName", email, "password", false);
        userDAO.create(user);
    }

    @After
    public void tearDown() throws JDBCException {
        while (userDAO.getIdByEmail(email) != null) {
            userDAO.delete(userDAO.getIdByEmail(email));
        }
    }

    @Test
    public void testGetImageByUserId() throws Exception {
        Image image = imageDAO.getImageByUserId(userDAO.getIdByEmail(email));
        assertNotNull(image);
        assertEquals("defaultGamblingLogImage.png", image.getImageName());
        assertEquals(getResourceImageAsBlob().length(), image.getImage().length() - 148);
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetImageByUserIdNull() throws Exception {
        imageDAO.getImageByUserId(1L);
    }

    private Blob getResourceImageAsBlob () throws IOException, SQLException {
        InputStream inputStream = ImageDAOImplTest.class.getResourceAsStream("/images/defaultGamblingLogImage.png");
        return new javax.sql.rowset.serial.SerialBlob(ConvertInputStreamToByteArray.getBytesFromInputStream(inputStream));
    }

}