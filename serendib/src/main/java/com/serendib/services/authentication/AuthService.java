package com.serendib.services.authentication;

import java.util.Optional;

import com.serendib.models.IdType;
import com.serendib.models.User;
import com.serendib.repository.UserRepository;
import com.serendib.services.validation.IdentityValidator;
import com.serendib.services.validation.OtpValidator;
import com.serendib.services.validation.PasswordValidator;
import com.serendib.services.validation.UsernameValidator;
import com.serendib.services.validation.ValidationHandler;
import com.serendib.utils.Config;


public class AuthService {
    // have hard coded user

    private UserRepository userRepository = UserRepository.getInstance();
    private static final int MAX_FAILED_LOGIN_ATTEMPTS = Config.getInt("max_login_attempts");

    public AuthService() {
    }

    public User signUp(String username, String password, String idNumber, IdType idType) {
        // validation chain
        ValidationHandler usernameValidator = new UsernameValidator();
        ValidationHandler passwordValidator = new PasswordValidator();
        ValidationHandler identityValidator = new IdentityValidator(idType);
        ValidationHandler otpValidator = new OtpValidator();

        // set up the chain
        usernameValidator.setNext(passwordValidator)
                         .setNext(identityValidator)
                         .setNext(otpValidator);

        // run validation
        if (!usernameValidator.handle(username, password, idNumber, idType)) {
            return null; // Validation failed
        }

        // accept terms and conditions 
        // display terms and conditions link
        System.out.println("Terms and conditions: " + Config.get("terms_conditions_url"));

        System.out.println("Do you accept the terms and conditions? (y/n)");
        String response = System.console().readLine();
        if (!response.equals("y")) {
            System.err.println("You must accept the terms and conditions to proceed.");
            return null;
        }

        // validation passed
        User newUser = new User(username, username, password, null, null, null, null);
        userRepository.saveUser(newUser);

        return newUser;
    }

    public User validateUsernamePassword(String username, String password) {
        Optional<User> matchingUser = userRepository.getUserByUsername(username);
        if(matchingUser.isEmpty()) {
            System.err.println("Please enter a valid username");
            return null;
        } else {
            User user = matchingUser.get();
    
            if (user.isLocked()) {
                System.err.println("Your account is locked. Please try again later.");
                return null;
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
                return null;
            }

            user.resetFailedLoginAttempts();
            return user;
        }
    }
}
