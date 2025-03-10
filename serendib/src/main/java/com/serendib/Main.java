package com.serendib;

import java.util.Iterator;
import java.util.Scanner;

import com.serendib.models.Account;
import com.serendib.models.IdType;
import com.serendib.models.IdentityDocument;
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
                // enter 1 to enter NIC, 2 for passport
                System.out.print("Enter 1 for NIC, 2 for passport: ");
                int idType = scanner.nextInt();
                while (idType != 1 && idType != 2) {
                    System.out.print("Invalid choice. Enter 1 for NIC, 2 for passport: ");
                    idType = scanner.nextInt();
                }
                
                IdentityDocument id = null;
                switch(idType){
                    case 1: // NIC
                        System.out.print("Enter your NIC: ");
                        String idInput = scanner.next();
                        id = new IdentityDocument(IdType.NIC, idInput);
                        break;
                    case 2: // passport
                        System.out.print("Enter your passport: ");
                        String passport = scanner.next();
                        id = new IdentityDocument(IdType.PASSPORT, passport);
                        break;
                }

                AuthService authService = new AuthService();
                loggedInUser = authService.signUp(newUsername, newPassword, id.getIdNumber(), id.getIdType(), "123456");
                break;
            case 3:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        if(loggedInUser != null) {
            System.out.println("Welcome " + loggedInUser.getUsername() + " to Serendib Digital Dashboard!");
            
            Iterator<Account> accountIterator = loggedInUser.iterator();

            System.out.println();
            // if user has accounts
            if (!accountIterator.hasNext()) {
                System.out.println("You have no accounts.");
            } else {
                while (accountIterator.hasNext()) {
                    Account account = accountIterator.next();
                    System.out.println("Account: " + account.getAccountNumber() + " (" + account.getAccountType() + ")");
                    // facility iterator
                    Iterator<String> facilityIterator = account.iterator();
                    while (facilityIterator.hasNext()) {
                        System.out.println("\t- " + facilityIterator.next());
                    }
                }
            }
        }
    }
}