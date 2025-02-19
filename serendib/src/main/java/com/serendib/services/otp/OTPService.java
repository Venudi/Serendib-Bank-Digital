package com.serendib.services.otp;
import java.security.SecureRandom;

public class OTPService {
    // otp length
    private static final int OTP_LENGTH = 6;
    // public String generateOTP(String secretKey, int length=OTP_LENGTH);
    public boolean sendOTP(String userId, String method){
        // TODO: send otp to user
        return true;
    }

    public static String generateSecretKey()
    {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
         
          secureRandom.nextBytes(bytes);
        StringBuilder secretKey = new StringBuilder();
       
        for (byte b : bytes) {
            secretKey.append(String.format("%02x", b));
        }
       
        return secretKey.toString();
    }
 
    // reference: https://www.geeksforgeeks.org/one-time-password-otp-algorithm-in-cryptography/
    // Function to generate a One Time Password (OTP) using
    // the secret key
    public static String generateOTP(String secretKey)
    {
        String allowedCharacters = "0123456789";
        StringBuilder otp = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
       
        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(allowedCharacters.length());
            otp.append(allowedCharacters.charAt(randomIndex));
        }
       
        return otp.toString();
    }

    public static boolean validateOTP(String enteredOTP, String generatedOTP)
    {
        return enteredOTP.equals(generatedOTP);
    }
}