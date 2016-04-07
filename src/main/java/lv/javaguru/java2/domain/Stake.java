package lv.javaguru.java2.domain;

import java.util.Date;

/**
 * Created by tyoma17 on 29.03.2016.
 */
public class Stake {
    private long stakeID;
    private Date date;
    private String url;
    private String event;
    private String betType;
    private double betAmount;
    private double coefficient;
    private String result;
    private String comment;
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

