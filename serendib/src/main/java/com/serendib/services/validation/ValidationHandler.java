package com.serendib.services.validation;

import com.serendib.models.IdType;

public abstract class ValidationHandler {
    protected ValidationHandler nextHandler;

    public ValidationHandler setNext(ValidationHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler; // Allows chaining
    }

    public boolean handle(String username, String password, String idNumber, IdType idType) {
        if (nextHandler != null) {
            return nextHandler.handle(username, password, idNumber, idType);
        }
        return true; // If no handler fails, validation passes
    }
}