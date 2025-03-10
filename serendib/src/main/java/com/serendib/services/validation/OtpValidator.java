package com.serendib.services.validation;

import com.serendib.models.IdType;

public class OtpValidator extends ValidationHandler {
    @Override
    public boolean handle(String username, String password, String idNumber, IdType idType, String otp) {
        if (!otp.matches("\\d{6}")) {
            System.err.println("Invalid OTP.");
            return false;
        }
        return super.handle(username, password, idNumber, idType, otp);
    }
}