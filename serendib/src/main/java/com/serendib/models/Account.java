package com.serendib.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountType;
    private List<String> facilities;
    private User user;
    private double balance;

    public Account() {
        
        this.facilities = new ArrayList<>();
    }
    
    public Account(String accountNumber, String accountType, double balance, User user) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.facilities = new ArrayList<>();
        this.balance = balance;
        this.user = user;
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { this.facilities = facilities; }
    public void addFacility(String facility) { this.facilities.add(facility); }
    public void removeFacility(String facility) { this.facilities.remove(facility); }

    public FacilityIterator iterator() {
        return new FacilityIterator(facilities);
    }

    public class FacilityIterator implements Iterator<String> {
        private List<String> facilities;
        private int index = 0;

        public FacilityIterator(List<String> facilities) {
            this.facilities = facilities;
        }

        @Override
        public boolean hasNext() {
            return index < facilities.size();
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                return facilities.get(index++);
            }
            return null;
        }
    }

}
