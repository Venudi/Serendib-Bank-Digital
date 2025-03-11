package com.serendib.repository;

import java.util.HashMap;
import java.util.Map;

import com.serendib.models.Account;

public class AccountRepository {
    private static AccountRepository instance;  // Singleton instance
    private Map<String, Account> accountMap;

    public AccountRepository() {
        accountMap = new HashMap<>();
        
        loadAccounts();
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            synchronized (AccountRepository.class) {
                if (instance == null) {
                    instance = new AccountRepository();
                }
            }
        }
        return instance;
    }

    // Load hardcoded accounts and facilities
    private void loadAccounts() {
    }

    // Check if account exists
    public boolean accountExists(String accountNumber) {
        return accountMap.containsKey(accountNumber);
    }

    // Get account by account number
    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    // Add a new account
    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    // Remove an account
    public void removeAccount(String accountNumber) {
        accountMap.remove(accountNumber);
    }
}
