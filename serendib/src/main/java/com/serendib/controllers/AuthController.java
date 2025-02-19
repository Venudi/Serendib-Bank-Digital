package com.serendib.controllers;

import com.serendib.services.authentication.AuthService;
import java.util.Scanner;

public class AuthController {
    private final AuthService authService;
    private final Scanner scanner;

    public AuthController() {
        this.authService = new AuthService();
        this.scanner = new Scanner(System.in);
    }

    public void signUp() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.signUp(username, password)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public void logIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.logIn(username, password)) {
            System.out.println("Login successful! Welcome to the dashboard.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
