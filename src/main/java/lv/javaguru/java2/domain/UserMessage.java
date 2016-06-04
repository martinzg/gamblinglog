package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_messages")
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID", nullable = false)
    private long id;

    @Column (name = "UserFrom", nullable = false)
    private String userFrom;

    @Column (name = "UserTo", nullable = false)
    private String userTo;

    @Column (name = "TimeStamp", nullable = false)
    private Date dateTime;

    @Column (name = "Message", nullable = false)
    private String message;

    @Column (name = "ReadState", nullable = false)
    private Boolean readState;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUserFrom(){
        return userFrom;
    }

    public void setUserFrom(String userFrom){
        this.userFrom = userFrom;
    }

    public String getUserTo(){
        return userTo;
    }

    public void setUserTo(String userTo){
        this.userTo = userTo;
    }

    public Date getDateTime(){
        return dateTime;
    }

    public void setDateTime(Date dateTime){
        this.dateTime = dateTime;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Boolean getReadState(){
        return readState;
    }

    public void setReadState(Boolean readState){
        this.readState = readState;
    }

}

