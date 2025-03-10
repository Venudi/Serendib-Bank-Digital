package com.serendib.services.authentication;

import java.util.Scanner;

import com.serendib.models.User;
import com.serendib.services.otp.OTPService;

public class LoginFacade {
    private AuthService authService;
    private OTPService otpService;

    private int loginAttempts = 0;
    // TODO: Session
    // private SessionService sessionService;

    public LoginFacade() {
        this.authService = new AuthService();
        this.otpService = new OTPService();
    }

    /**
     * Authenticates a user using username/password and OTP.
     * @param username The user's username.
     * @param password The user's password.
     * @param otp The one-time password for 2FA.
     * @return true if authentication is successful; otherwise false.
     */
    public User authenticate(String username, String password) {
        User user = authService.logIn(username, password);
        if (user == null) {
            return null;
        }

        String storedOtp = otpService.generateOTP(username);
        System.out.println("storedOtp - " + storedOtp);

        // read otp from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter OTP: ");
        String otp = scanner.nextLine();
        scanner.close();

        if (storedOtp == null || !otpService.validateOTP(otp, storedOtp)) {
            System.err.println("Invalid OTP.");
            return null;
        }

        // TODO: create secure session
        System.out.println("User authenticated successfully.");
        return user;
    }
}
