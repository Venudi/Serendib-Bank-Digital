package com.serendib;

import java.util.Iterator;
import java.util.Scanner;

import com.serendib.models.Account;
import com.serendib.models.IdType;
import com.serendib.models.IdentityDocument;
import com.serendib.models.User;
import com.serendib.services.authentication.AuthService;
import com.serendib.services.authentication.LoginFacade;

public class Main {
    public static void main(String[] args) {
        User loggedInUser;
        
        loggedInUser = loginRegisterOrExit();

        clearScreen();

        if(loggedInUser != null) {
            // print dashed line
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome " + loggedInUser.getUsername() + " to Serendib Digital Dashboard!");
            System.out.println("--------------------------------------------------");

            displayUserAccounts(loggedInUser);

        }
    }

    public static void displayMainMenu() {
        System.out.println("Welcome to Serendib Digital!");
        System.out.println("Press 1 to log in");
        System.out.println("Press 2 to sign up");
        System.out.println("Press 3 to exit");
    }

    public static User loginRegisterOrExit() {
        displayMainMenu();

        Scanner scanner = new Scanner(System.in);

        User loggedInUser = null;
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                while (loggedInUser == null) {
                    System.out.print("Enter your username: ");
                    String username = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = new String(System.console().readPassword());

                    LoginFacade loginFacade = new LoginFacade();
                    loggedInUser = loginFacade.authenticate(username, password);
                    if (loggedInUser == null) {
                        System.out.println("Login failed. Please try again.");
                    }
                }
            }
            case 2 -> {
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
                    case 1 -> {
                        // NIC
                        System.out.print("Enter your NIC: ");
                        String idInput = scanner.next();
                        id = new IdentityDocument(IdType.NIC, idInput);
                }
                    case 2 -> {
                        // passport
                        System.out.print("Enter your passport: ");
                        String passport = scanner.next();
                        id = new IdentityDocument(IdType.PASSPORT, passport);
                }
                }
                AuthService authService = new AuthService();
                loggedInUser = authService.signUp(newUsername, newPassword, id.getIdNumber(), id.getIdType());
            }
            case 3 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice");
        }
        scanner.close();
        return loggedInUser;
    }

    // display user accounts
    public static void displayUserAccounts(User user) {
        System.out.println("YOUR ACCOUNTS");
        Iterator<Account> accountIterator = user.iterator();


        // if user has accounts
        if (!accountIterator.hasNext()) {
            System.out.println("You have no accounts.");
        } else {
            while (accountIterator.hasNext()) {
                Account account = accountIterator.next();
                System.out.println("Account: " + account.getAccountNumber() + " (" + account.getAccountType() + ")");
                System.out.println("\t- Balance (LKR): " + String.format("%.2f", account.getBalance()));
                // facility iterator
                Iterator<String> facilityIterator = account.iterator();
                while (facilityIterator.hasNext()) {
                    System.out.println("\t- " + facilityIterator.next());
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}