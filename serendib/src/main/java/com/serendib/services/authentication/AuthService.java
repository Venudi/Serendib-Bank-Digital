package com.serendib.services.authentication;

import java.util.Optional;

import com.serendib.models.User;
import com.serendib.repository.UserRepository;


public class AuthService {
    // have hard coded user

    // private UserRepository userRepository;
    private UserRepository userRepository = UserRepository.getInstance();
    private static final int MAX_FAILED_LOGIN_ATTEMPTS = 3;

    public AuthService() {
        // this.userRepository = userRepository;
        // this.userService = new UserService();
    }

    public boolean signUp(String username, String password) {
        
        if (userRepository.getUserByUsername(username) != null) {
            return false;
        }
        // TODO
        User newUser = new User(username, username, password, null, null, null, null);

        return userRepository.saveUser(newUser);
    }

    // TODO: Allow only 3 login attempts
    public boolean logIn(String username, String password) {
        Optional<User> matchingUser = userRepository.getUserByUsername(username);
        if(matchingUser.isEmpty()) {
            System.err.println("Please enter a valid username");
            return false;
        } else {
            User user = matchingUser.get();
    
            if (user.isLocked()) {
                System.err.println("Your account is locked. Please try again later.");
                return false;
            }
    
            if (!user.getPassword().equals(password)) {
                System.err.println("Your username or password is incorrect. Please try again.");
                user.incrementFailedLoginAttempts();
                
                // lock user after max failed login attempts
                if (user.getFailedLoginAttempts() >= MAX_FAILED_LOGIN_ATTEMPTS) {
                    user.lockAccount();
                    System.err.println("Your account has been locked. Please try again later.");
                }
    
                userRepository.saveUser(user);
                return false;
            }
            
            System.out.println("Welcome " + user.getUsername());
            user.resetFailedLoginAttempts();
            return true;
        }

    }

}
