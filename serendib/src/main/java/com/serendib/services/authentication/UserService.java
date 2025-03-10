package com.serendib.services.authentication;

import java.util.List;
import java.util.Optional;

import com.serendib.models.User;
import com.serendib.repository.UserRepository;
import com.serendib.utils.JsonReader;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public static List<User> getAllUsers() {
        return JsonReader.readUsers();
    }
    
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        return user.orElse(null);  // Return null if not found
    }

    public boolean updateEmail(String username, String newEmail) {
        User user = getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return userRepository.saveUser(user);
    }
}
