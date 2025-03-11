package com.serendib.services.otp;

public class EmailOTPHandler extends OTPHandler {
    @Override
    public void sendOTP(String otp, String mobile, String email) {
        if (email != null && !email.isEmpty()) {
            System.out.println("OTP sent to email: " + email + " (" + otp + ")");
        } else if (nextHandler != null) {
            nextHandler.sendOTP(otp, mobile, email); // Pass to next handler
        }
    }
}