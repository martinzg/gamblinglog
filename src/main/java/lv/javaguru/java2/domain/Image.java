package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.sql.Blob;

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

}

