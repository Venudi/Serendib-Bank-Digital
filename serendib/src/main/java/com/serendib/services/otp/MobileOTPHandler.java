package com.serendib.services.otp;

public class MobileOTPHandler extends OTPHandler {
    @Override
    public void sendOTP(String otp, String mobile, String email) {
        if (mobile != null && !mobile.isEmpty()) {
            System.out.println("OTP sent to mobile: " + mobile);
        } 
        
        if (nextHandler != null) {
            nextHandler.sendOTP(otp, mobile, email); // Pass to next handler
        }
    }
}