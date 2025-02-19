package com.serendib.repository;

import java.util.HashSet;
import java.util.Set;

public class AccountRepository {
    private Set<String> accounts; // Stores valid account numbers

    public AccountRepository() {
        accounts = new HashSet<>();
        // Accounts
        accounts.add("0011223344");
        accounts.add("9876543210");
        accounts.add("1234567890");
    }

    public boolean accountExists(String accountNumber) {
        return accounts.contains(accountNumber);
    }
}
