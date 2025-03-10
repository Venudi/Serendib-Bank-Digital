package com.serendib.models;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class User {
    private String userId;
    private String username;
    private String password;
    private IdentityDocument identityDocument;
    private String email;
    private String mobile;
    private Account account;
    private UserState state; // ACTIVE, LOCKED
    private int failedLoginAttempts;
    private LocalDateTime lockTime;

    // public final int MAX_FAILED_LOGIN_ATTEMPTS = 3;
    // private static final int LOCK_DURATION_MINUTES = 1;
    // private static final int LOCK_DURATION_SECONDS = 15;

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

    public User(String userId, String username, String password, IdentityDocument identityDocument, String email, String mobile, Account account) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.identityDocument = identityDocument;
        this.email = email;
        this.mobile = mobile;
        this.account = account;
        this.state = UserState.ACTIVE;
        this.failedLoginAttempts = 0;
    }

    // TODO: Adjust getters and setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getEmail() { return email; }
    public String getMobile() { return mobile; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

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
        }, 15000);
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