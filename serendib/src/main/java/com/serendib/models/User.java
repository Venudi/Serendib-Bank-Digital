package com.serendib.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.serendib.utils.Config;

public class User {
    private String userId;
    private String username;
    private String password;
    private IdentityDocument identityDocument;
    private String email;
    private String mobile;
    // account list
    private List<Account> accounts = new ArrayList<>();
    private UserState state; // ACTIVE, LOCKED
    private int failedLoginAttempts;
    private LocalDateTime lockTime;

    // private static final int LOCK_DURATION_MINUTES = 1;
    private static final int LOCK_DURATION_SECONDS = Config.getInt("timeout_duration_s");

    public User() {
        this.state = UserState.ACTIVE;
        this.failedLoginAttempts = 0;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.failedLoginAttempts = 0;
        this.state = UserState.ACTIVE;
        this.lockTime = null;
    }

    public User(String userId, String username, String password, IdentityDocument identityDocument, String email, String mobile, List<Account> accounts) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.identityDocument = identityDocument;
        this.email = email;
        this.mobile = mobile;
        this.accounts = accounts;
        this.state = UserState.ACTIVE;
        this.failedLoginAttempts = 0;
    }

    // TODO: Adjust getters and setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getEmail() { return email; }
    public String getMobile() { return mobile; }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    // add a new account
    public void addAccount(Account account) {
        // if accounts is null, create a new list
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setUser(this);
    }

    public Iterator<Account> iterator() {
        return new AccountIterator(accounts);
    }

    public class AccountIterator implements Iterator<Account> {
        private List<Account> accounts;
        private int currentIndex = 0;

        public AccountIterator(List<Account> accounts) {
            this.accounts = accounts != null ? accounts : new ArrayList<>(); 
        }

        @Override
        public boolean hasNext() {
            return currentIndex < accounts.size();
        }

        @Override
        public Account next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more accounts");
            }
            return accounts.get(currentIndex++);
        }
    }

    public Account.FacilityIterator getFacilityIterator(Account account) {
        return account.iterator();
    }

    public UserState getState() { return state; }
    public void setState(UserState state) { this.state = state; }

    public IdentityDocument getIdentityDocument() { return identityDocument; }

    public boolean isLocked() {
        // if (state == UserState.LOCKED) {
        //     return true;
        //     // if (lockTime != null && lockTime.plusSeconds(LOCK_DURATION_SECONDS).isAfter(LocalDateTime.now())) {
        //     //     return true; // Account is still locked
        //     // }
        //     // // Account is locked but lock time has expired
        //     // state = UserState.ACTIVE;

        //     // // print unlock time
        //     // System.out.println("Account unlocked at: " + LocalDateTime.now());
        //     // lockTime = null;
        // }
        return state == UserState.LOCKED;
    }

    public void lockAccount() {
        this.state = UserState.LOCKED;
        this.lockTime = LocalDateTime.now();
        System.out.println("Account locked at: " + lockTime);
        System.err.println("Account locked until: " + lockTime.plusMinutes(1));

        // unlock account after 1 minute
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                unlockAccount();
            }
        }, LOCK_DURATION_SECONDS*1000);
    }

    private void unlockAccount() {
        this.state = UserState.ACTIVE;
        this.lockTime = null;
        System.out.println("Account unlocked at: " + LocalDateTime.now());
        this.failedLoginAttempts = 0;
    }

    public void incrementFailedLoginAttempts() {
        this.failedLoginAttempts++;
        System.out.println("Failed login attempts: " + this.failedLoginAttempts);
    }

    public void resetFailedLoginAttempts() {
        this.failedLoginAttempts = 0;
    }

    public int getFailedLoginAttempts() {
        return this.failedLoginAttempts;
    }

}