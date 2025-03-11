package com.serendib.services.validation;

import java.util.Scanner;

import com.serendib.models.IdType;
import com.serendib.services.otp.OTPService;

public class OtpValidator extends ValidationHandler {
    @Override
    public boolean handle(String username, String password, String idNumber, IdType idType) {
        OTPService otpService = new OTPService();
        String generatedOTP = otpService.sendOTP(username, "0771234567", "user@email.com");
        
        // scan OTP from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter OTP: ");
        String enteredOTP = scanner.nextLine();
        // scanner.close();

        if (!enteredOTP.equals(generatedOTP)) {
            System.err.println("Invalid OTP");
            return false;
        }
        
        System.out.println("OTP verified!");
        return super.handle(username, password, idNumber, idType);
    }
}