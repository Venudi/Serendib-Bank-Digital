package com.serendib.services.validation;

import com.serendib.models.IdType;
import com.serendib.repository.UserRepository;

public class UsernameValidator extends ValidationHandler {
    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public boolean handle(String username, String password, String idNumber, IdType idType) {
        if (userRepository.getUserByUsername(username).isPresent()) {
            System.err.println("Username already exists.");
            return false;
        }
        return super.handle(username, password, idNumber, idType);
    }
}
