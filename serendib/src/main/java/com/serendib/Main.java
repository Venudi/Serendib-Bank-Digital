package com.serendib;

import com.serendib.models.IdType;
import com.serendib.models.UserOnboardingRequest;
import com.serendib.repository.AccountRepository;
import com.serendib.services.authentication.AuthService;
import com.serendib.services.validation.NicValidator;
import com.serendib.services.validation.OtpValidator;
import com.serendib.services.validation.PassportValidator;
import com.serendib.services.validation.ValidationService;
import com.serendib.services.onboarding.OnboardingService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ValidationService nicValidator = new NicValidator();
        ValidationService passportValidator = new PassportValidator();
        ValidationService otpValidator = new OtpValidator();
        AccountRepository accountRepository = new AccountRepository();

        OnboardingService onboardingService = new OnboardingService(nicValidator, passportValidator, otpValidator, accountRepository);

        // Valid request
        UserOnboardingRequest validRequest = new UserOnboardingRequest(IdType.NIC, "987654321V", "00223344", "123456");
        onboardingService.onboardUser(validRequest);

        // Invalid OTP request
        UserOnboardingRequest invalidOtpRequest = new UserOnboardingRequest(IdType.NIC, "987654321V", "0011223344", "999999");
        onboardingService.onboardUser(invalidOtpRequest);

        // Invalid passport request
        UserOnboardingRequest invalidPassportRequest = new UserOnboardingRequest(IdType.PASSPORT, "1234567", "0011223344", "123456");
        onboardingService.onboardUser(invalidPassportRequest);

        // Valid passport request
        UserOnboardingRequest validPassportRequest = new UserOnboardingRequest(IdType.PASSPORT, "N1234567", "0011223344", "123456");
        onboardingService.onboardUser(validPassportRequest);

    }
}