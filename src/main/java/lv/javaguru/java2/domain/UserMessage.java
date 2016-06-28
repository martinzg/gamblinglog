package lv.javaguru.java2.domain;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserMessage)){
            return false;
        }
        UserMessage msg = (UserMessage) o;
        return (this.id == msg.getId()) &&
               (this.userFrom.equals(msg.getUserFrom())) &&
               (this.userTo.equals(msg.getUserTo())) &&
               (changeDateFormat(this.dateTime).equals(msg.getDateTime().toString())) &&
               (this.message.equals(msg.getMessage())) &&
               (this.readState.equals(msg.getReadState()));
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userFrom.hashCode();
        result = 31 * result + userTo.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + readState.hashCode();
        return result;
    }

    private String changeDateFormat(Date date){
        Date roundedDateTime = DateUtils.round(date, Calendar.SECOND);
        String NEW_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
        SimpleDateFormat sdf = new SimpleDateFormat(NEW_DATE_FORMAT);
        return sdf.format(roundedDateTime);
    }

}

