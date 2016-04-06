package test.lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.User;

public class UserCreator {

    public static User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}
