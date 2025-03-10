package com.serendib;

import java.util.Scanner;

import com.serendib.services.authentication.AuthService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Serendib Digital!");
        System.out.println("Press 1 to log in");
        System.out.println("Press 2 to sign up");
        System.out.println("Press 3 to exit");
        Scanner scanner = new Scanner(System.in);

        boolean loggedIn = false;

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                while (!loggedIn) {
                    System.out.print("Enter your username: ");
                    String username = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = new String(System.console().readPassword());
                    AuthService authService = new AuthService();
                    Boolean loginSuccessful = authService.logIn(username, password);
                    if (loginSuccessful) {
                        System.out.println("Login successful!");
                        loggedIn = true;
                    }
                }

                break;
            case 2:
                System.out.print("Enter your username: ");
                String newUsername = scanner.next();
                System.out.print("Enter your password: ");
                // hide password as the user types
                String newPassword = new String(System.console().readPassword());

                // AuthService authService = new AuthService();
                // authService.signUp(newUsername, newPassword);
                break;
            case 3:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }


        System.out.println("Welcome to Serendib Digital Dashboard!");
        // userRepository.loadUsers();

        
        // ValidationService nicValidator = new NicValidator();
        // ValidationService passportValidator = new PassportValidator();
        // ValidationService otpValidator = new OtpValidator();
        // AccountRepository accountRepository = new AccountRepository();

        // OnboardingService onboardingService = new OnboardingService(nicValidator, passportValidator, otpValidator, accountRepository);

        // // Valid request
        // UserOnboardingRequest validRequest = new UserOnboardingRequest(IdType.NIC, "987654321V", "0011223344", "123456");
        // onboardingService.onboardUser(validRequest);

        // // Invalid OTP request
        // UserOnboardingRequest invalidOtpRequest = new UserOnboardingRequest(IdType.NIC, "987654321V", "0011223344", "999999");

        // onboardingService.onboardUser(invalidOtpRequest);

        // // Invalid passport request
        // UserOnboardingRequest invalidPassportRequest = new UserOnboardingRequest(IdType.PASSPORT, "1234567", "0011223344", "123456");
        // onboardingService.onboardUser(invalidPassportRequest);

        // // Valid passport request
        // UserOnboardingRequest validPassportRequest = new UserOnboardingRequest(IdType.PASSPORT, "N1234567", "0011223344", "123456");
        // onboardingService.onboardUser(validPassportRequest);

        // // User login
        // LoginFacade loginFacade = new LoginFacade();
        // boolean isAuthenticated = loginFacade.authenticate("user123", "pass123", "123456");
        // System.out.println("Authentication result: " + isAuthenticated);
    }
}