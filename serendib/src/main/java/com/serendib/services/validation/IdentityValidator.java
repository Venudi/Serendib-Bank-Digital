package com.serendib.services.validation;

import com.serendib.models.IdType;

public class IdentityValidator extends ValidationHandler {
    private IdType idType;

    public IdentityValidator(IdType idType) {
        this.idType = idType;
    }

    @Override
    public boolean handle(String username, String password, String idNumber, IdType idType) {
        boolean isValid = (idType == IdType.NIC && idNumber.matches("\\d{9}[Vv]?|\\d{12}")) ||
                          (idType == IdType.PASSPORT && idNumber.matches("[A-Z]{1}[0-9]{7}"));

        if (!isValid && idType == IdType.NIC) {
            System.err.println("Invalid NIC number.");
            return false;
        }
        if (!isValid && idType == IdType.PASSPORT) {
            System.err.println("Invalid passport number.");
            return false;
        }
        return super.handle(username, password, idNumber, idType);
    }
}