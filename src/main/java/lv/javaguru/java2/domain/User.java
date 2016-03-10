package lv.javaguru.java2.domain;

/**
 * Created by Viktor on 01/07/2014.
 */
public class User {

    private long userId;
    private String firstName;
    private String lastName;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
