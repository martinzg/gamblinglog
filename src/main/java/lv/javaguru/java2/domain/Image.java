package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

@Entity
@Table(name = "user_images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID", nullable = false)
    private long id;

    @Column (name = "UserID", nullable = false)
    private long userId;

    @Column (name = "ImageName", nullable = false)
    private String imageName;

    @Column (name = "Image")
    private Blob image;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getImageName(){
        return imageName;
    }

    public void setImageName(String imageName){
        this.imageName = imageName;
    }

    public Blob getImage(){
        return image;
    }

    public void setImage(Blob image){
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Image)){
            return false;
        }
        Image img = (Image) o;
        return this.id == img.getId() &&
                this.userId == (img.getUserId()) &&
                this.imageName.equals(img.getImageName()) &&
                compareBlobLength(img) &&
                compareBlobBytes(img);
    }

    private Boolean compareBlobLength(Image img){
        try{
            return this.image.length() == img.getImage().length();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private Boolean compareBlobBytes(Image img){
        try{
            byte[] thisArray = this.image.getBytes(1L, (int)image.length());
            byte[] imgArray = img.getImage().getBytes(1L, (int)img.getImage().length());
            return Arrays.equals(thisArray, imgArray);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}

