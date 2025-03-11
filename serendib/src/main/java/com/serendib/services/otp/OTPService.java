package com.serendib.services.otp;

import java.security.SecureRandom;

public class OTPService {
    private static final int OTP_LENGTH = 6;
    private OTPHandler otpHandler;

    public OTPService() {
        // Set up the chain of responsibility: Mobile -> Email
        OTPHandler mobileHandler = new MobileOTPHandler();
        OTPHandler emailHandler = new EmailOTPHandler();
        mobileHandler.setNextHandler(emailHandler);

        this.otpHandler = mobileHandler;
    }

    public boolean sendOTP(String username, String mobile, String email) {
        String otp = generateOTP(username);
        if (otp == null) {
            return false;
        }
        otpHandler.sendOTP(otp, mobile, email);
        return true;
    }

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);

        StringBuilder secretKey = new StringBuilder();
        for (byte b : bytes) {
            secretKey.append(String.format("%02x", b));
        }
        return secretKey.toString();
    }

    public static String generateOTP(String secretKey) {
        String allowedCharacters = "0123456789";
        StringBuilder otp = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(allowedCharacters.length());
            otp.append(allowedCharacters.charAt(randomIndex));
        }
        return otp.toString();
    }

    public static boolean validateOTP(String enteredOTP, String generatedOTP) {
        return enteredOTP.equals(generatedOTP);
    }
}
