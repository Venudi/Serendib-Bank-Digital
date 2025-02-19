package com.serendib.models;

import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private String accountType; // CASA
    private List<String> facilities; // subscribed facilities

    public Account(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.facilities = new ArrayList<>();
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { this.facilities = facilities; }
    public void addFacility(String facility) { this.facilities.add(facility); }
    public void removeFacility(String facility) { this.facilities.remove(facility); }
}
