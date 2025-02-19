package com.serendib.services.authentication;

import com.serendib.models.User;
import com.serendib.utils.JsonUtil;
import java.util.List;

public class UserService {
    private static final String FILE_PATH = "data/users.json";

    public List<User> getAllUsers() {
        return JsonUtil.readUsersFromFile(FILE_PATH);
    }

    public User getUserByUsername(String username) {
        return getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public boolean saveUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        return JsonUtil.writeUsersToFile(FILE_PATH, users);
    }

}
