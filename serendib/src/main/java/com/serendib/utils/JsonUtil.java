package com.serendib.utils;

import com.serendib.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUsersFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static boolean writeUsersToFile(String filePath, List<User> users) {
        try {
            objectMapper.writeValue(new File(filePath), users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
