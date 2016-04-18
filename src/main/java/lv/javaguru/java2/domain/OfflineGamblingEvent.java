package lv.javaguru.java2.domain;

import java.util.Date;
import java.util.List;

public class OfflineGamblingEvent {

    private Long userId;
    private Date date;
    private String comment;
    private Long placeId;

    public List<Long> getGameId() {
        return gameId;
    }

    public void setGameId(List<Long> gameId) {
        this.gameId = gameId;
    }

    private List<Long> gameId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }



}
