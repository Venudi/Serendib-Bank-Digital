package com.serendib.controllers;

import java.util.Scanner;

import com.serendib.services.authentication.AuthService;

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

        // if (authService.signUp(username, password) != null) {
        //     System.out.println("User registered successfully!");
        // } else {
        //     System.out.println("Username already exists.");
        // }
    }

    public boolean logIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // login attempts
        if (authService.logIn(username, password) != null) {
            System.out.println("Login successful! Welcome to the dashboard.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }
}
