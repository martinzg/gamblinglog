package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stakes")

/**
 * Created by tyoma17 on 29.03.2016.
 */
public class Stake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StakeID", nullable = false)
    private long stakeID;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "Event", nullable = false)
    private String event;

    @Column(name = "BetType", nullable = false)
    private String betType;

    @Column(name = "BetAmount", nullable = false)
    private double betAmount;

    @Column(name = "Coefficient", nullable = false)
    private double coefficient;

    @Column(name = "Result", nullable = false)
    private String result;

    @Column(name = "Comment", nullable = false)
    private String comment;

    @Column(name = "UserID", nullable = false)
    private Long userId;

    public long getStakeID() {
        return stakeID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getID() {
        return stakeID;
    }

    public void setStakeID(long stakeID) {
        this.stakeID = stakeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

