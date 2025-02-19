package com.serendib.services.validation;

public class OtpValidator implements ValidationService {
    @Override
    public boolean validate(String input) {
        // TODO: check if the user input otp matches the generated otp
        return true;
    }
}
