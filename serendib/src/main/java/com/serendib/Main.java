package com.serendib;

import java.util.Iterator;
import java.util.Scanner;

import com.serendib.models.Account;
import com.serendib.models.User;
import com.serendib.services.authentication.AuthService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Serendib Digital!");
        System.out.println("Press 1 to log in");
        System.out.println("Press 2 to sign up");
        System.out.println("Press 3 to exit");
        Scanner scanner = new Scanner(System.in);

        User loggedInUser = null;

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                while (loggedInUser == null) {
                    System.out.print("Enter your username: ");
                    String username = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = new String(System.console().readPassword());
                    AuthService authService = new AuthService();
                    loggedInUser = authService.logIn(username, password);
                    if (loggedInUser != null) {
                        System.out.println("Login successful!");
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

        if(loggedInUser != null) {
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
            System.out.println("Welcome to Serendib Digital Dashboard!");
            // System.out.println("Press 1 to view your subscribed facilities");
            // System.out.println("Press 2 to subscribe to new facilities");

            Iterator<Account> accountIterator = loggedInUser.iterator();

            while (accountIterator.hasNext()) {
                Account account = accountIterator.next();
                System.out.println("Account: " + account.getAccountNumber() + " (" + account.getAccountType() + ")");
            }

            // display facilities using iterator
            // for each account in getAccounts
            // for (int i = 0; i < loggedInUser.getAccounts().size(); i++) {
            //     Account account = loggedInUser.getAccounts().get(i);
            //     // print account details
            //     System.out.println("Account Number: " + account.getAccountNumber());
            //     System.out.println("Account Type: " + account.getAccountType());
            //     System.out.println("Facilities: ");
            //     account.iterator().forEachRemaining(facility -> System.out.println(facility));
            //     System.out.println();
            // }
        }
        // clear terminal
    }
}