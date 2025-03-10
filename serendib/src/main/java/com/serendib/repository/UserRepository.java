package com.serendib.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.serendib.models.Account;
import com.serendib.models.User;


public class UserRepository {
    // private static final String FILE_PATH = "/data/users.json";
    private static UserRepository instance;
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        loadUsers();
    }

    // Static method to get the single instance
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private void loadUsers() {
        if (users.isEmpty()) {
            // hard coded users
            User user = new User("1", "admin", "admin", null, null, null, null);
            users.add(user);
            
            Account account = new Account("1", "CASA", users.get(0));
            account.addFacility("ATM Withdrawal");
            account.addFacility("Online Banking");
            account.addFacility("Loan Services");
            List<Account> accounts = new ArrayList<>();
            accounts.add(account);
            user.addAccount(account);

            
            user = new User("2", "user1", "user1", null, null, null, null);
            users.add(user);
            account = new Account("2", "Savings", users.get(1));
            account.addFacility("ATM Withdrawal");
            account.addFacility("Online Banking");
            accounts = new ArrayList<>();
            accounts.add(account);
            user.addAccount(account);

            account = new Account("3", "Current", users.get(1));
            account.addFacility("ATM Withdrawal");
            account.addFacility("Online Banking");
            account.addFacility("Cheque Services");
            accounts.add(account);
            user.addAccount(account);
            
            users.add(new User("3", "user2", "user2", null, null, null, null));
        }
    }

    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public boolean saveUser(User updatedUser) {
        // Find the user in the list and update it
        // Find the user in the list and update it
        Optional<User> existingUser = getUserByUsername(updatedUser.getUsername());
        if (existingUser.isPresent()) {
            // Replace the existing user with the updated user
            users.remove(existingUser.get());
            users.add(updatedUser);
            return true;
        } else {
            // If the user doesn't exist, add the new user
            users.add(updatedUser);
            return true;
        }
    }


    public List<User> getAllUsers() {
        return users;
    }
}
