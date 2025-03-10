package com.serendib.services.authentication;

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
    public boolean authenticate(String username, String password, String otp) {
        if (!authService.logIn(username, password)) {
            return false;
        }

        // validate OTP
        if (!otpService.validateOTP(otp, otpService.generateOTP(username))) {
            return false;
        }

        // TODO: create secure session
        System.out.println("User authenticated successfully.");
        return true;
    }
}
