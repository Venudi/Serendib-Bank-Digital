package com.serendib.services.authentication;

import com.serendib.models.User;
import com.serendib.utils.JsonUtil;
import java.util.List;


public class AuthService {
    private final UserService userService;

    public AuthService() {
        this.userService = new UserService();
    }

    public boolean signUp(String username, String password) {
        if (userService.getUserByUsername(username) != null) {
            return false;
        }
        // TODO
        User newUser = new User(username, username, password, null, null, null, null);
        return userService.saveUser(newUser);
    }

    public boolean logIn(String username, String password) {
        User user = userService.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

}
