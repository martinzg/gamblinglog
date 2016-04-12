package lv.javaguru.java2.domain;

public class User {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof User)){
            return false;
        }
        User aUser = (User) obj;
        return (this.userId==(aUser.getUserId()))&&
                        (this.firstName.equals(aUser.getFirstName()))&&
                        (this.lastName.equals(aUser.getLastName()))&&
                        (this.email.equals(aUser.getEmail())&&
                        (this.password.equals(aUser.getPassword())));
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
