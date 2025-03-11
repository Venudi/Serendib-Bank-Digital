package com.serendib.services.otp;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class OTPService {
    private OTPHandler otpHandler;
    private static final int TIME_STEP = 30; 

    public OTPService() {
        // Set up the chain of responsibility: Mobile -> Email
        OTPHandler mobileHandler = new MobileOTPHandler();
        OTPHandler emailHandler = new EmailOTPHandler();
        mobileHandler.setNextHandler(emailHandler);

        this.otpHandler = mobileHandler;
    }

    public String sendOTP(String username, String mobile, String email) {
        String otp = generateTOTP(username);
        if (otp == null) {
            return null;
        }
        otpHandler.sendOTP(otp, mobile, email);
        return otp;
    }

    public static String generateTOTP(String secretKey) {
        long currentTime = System.currentTimeMillis() / 1000L;
        long timeCounter = currentTime / TIME_STEP;

        try {
            // Create HMAC-SHA1 hash from secret and timeCounter
            byte[] hmacSHA1 = hmacSHA1(secretKey, timeCounter);
            // Convert the HMAC result to OTP
            return generateOTP(hmacSHA1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creates an HMAC-SHA1 hash from the secret key and time counter
    private static byte[] hmacSHA1(String secretKey, long timeCounter) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        return mac.doFinal(longToBytes(timeCounter)); // Hash the time counter
    }

    // Converts long (time counter) to a byte array
    private static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (value >> (8 * (7 - i)) & 0xFF);
        }
        return bytes;
    }

    // Generates a 6-digit OTP from the HMAC result
    private static String generateOTP(byte[] hmacSHA1) {
        int offset = hmacSHA1[hmacSHA1.length - 1] & 0x0F;
        int binaryCode = ((hmacSHA1[offset] & 0x7f) << 24)
                | ((hmacSHA1[offset + 1] & 0xff) << 16)
                | ((hmacSHA1[offset + 2] & 0xff) << 8)
                | (hmacSHA1[offset + 3] & 0xff);

        // Generate OTP and ensure it's a 6-digit number
        int otp = binaryCode % 1000000; // Limit to 6 digits
        return String.format("%06d", otp); // Pad with leading zeros
    }


    public static boolean validateOTP(String enteredOTP, String generatedOTP) {
        return enteredOTP.equals(generatedOTP);
    }
}
