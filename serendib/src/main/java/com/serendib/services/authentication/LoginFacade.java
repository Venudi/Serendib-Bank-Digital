package com.serendib.services.authentication;

import java.util.Scanner;

import com.serendib.models.User;
import com.serendib.services.otp.OTPService;

public class LoginFacade {
    private AuthService authService;
    private OTPService otpService;

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
        User user = authService.validateUsernamePassword(username, password);
        if (user == null) {
            return null;
        }

        String storedOtp = otpService.sendOTP(username, "0771234567", "username@email.com");

        // read otp from user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press 1 to enter OTP\nPress 2 to request new OTP");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 2) {
            storedOtp = otpService.sendOTP(username, "0771234567", "username@email.com");
        }
        if (storedOtp == null) {
            System.err.println("Failed to generate OTP.");
            return null;
        }

        
        // allow three attempts to enter OTP
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter OTP: ");
            String otp = scanner.nextLine();

            if (otp.equals(storedOtp)) {
                System.out.println("OTP verified!");
                break;
            }
            
            if (i == 2) {
                System.err.println("Maximum attempts reached. Please try again later.");
                user.lockAccount();
                return null;
            }
        }
        
        System.out.println("User authenticated successfully.");
        scanner.close();

        return user;
    }
}
