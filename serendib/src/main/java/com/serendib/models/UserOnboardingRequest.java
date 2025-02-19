package com.serendib.models;

public class UserOnboardingRequest {
    private IdType idType;
    private String idNumber;
    private String accountNumber;
    private String otp;

    public UserOnboardingRequest(IdType idType, String idNumber, String accountNumber, String otp) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.accountNumber = accountNumber;
        this.otp = otp;
    }

    public IdType getIdType() { return idType; }
    public String getIdNumber() { return idNumber; }
    public String getAccountNumber() { return accountNumber; }
    public String getOtp() { return otp; }

    public void setIdType(IdType idType) { this.idType = idType; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setOtp(String otp) { this.otp = otp; }
}
