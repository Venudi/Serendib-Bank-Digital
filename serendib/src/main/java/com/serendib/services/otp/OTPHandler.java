package com.serendib.services.otp;

public abstract class OTPHandler {
    protected OTPHandler nextHandler;

    public void setNextHandler(OTPHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void sendOTP(String otp, String mobile, String email);
}