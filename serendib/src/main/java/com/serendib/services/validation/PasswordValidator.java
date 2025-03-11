package com.serendib.services.validation;

import com.serendib.models.IdType;

public class PasswordValidator extends ValidationHandler {
    @Override
    public boolean handle(String username, String password, String idNumber, IdType idType) {
        if (password.length() < 8 || password.length() > 15 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
            System.err.println("Password must be between 8 and 15 characters, contain at least one uppercase letter, and contain at least one digit.");
            return false;
        }
        return super.handle(username, password, idNumber, idType);
    }
}