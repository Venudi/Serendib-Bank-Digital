package com.serendib.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.serendib.models.User;

public class JsonReader {

    public static List<User> readUsers() {
        // ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = JsonReader.class.getResourceAsStream("/data/users.json")) {
            if (inputStream == null) {
                throw new RuntimeException("users.json file not found!");
            }
            // retrun dummy list
            return List.of(new User("user1", "user1", "password1", null, null, null, null));
            // return objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading users.json", e);
        }
    }

    // TODO: Implement this method
    public static boolean writeUsers(List<User> users) {
        // ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(JsonReader.class.getResource("/data/users.json").toURI());
            // objectMapper.writeValue(file, users);   // Write users list back to the JSON file
            return true;    
        } catch (Exception e) {
            throw new RuntimeException("Error writing users.json", e);
        }
    }

}
