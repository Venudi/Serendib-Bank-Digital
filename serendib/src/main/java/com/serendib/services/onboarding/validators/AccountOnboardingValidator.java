package com.serendib.services.onboarding.validators;

import com.serendib.models.UserOnboardingRequest;
import com.serendib.repository.AccountRepository;

public class AccountOnboardingValidator extends OnboardingValidator {
    private AccountRepository accountRepository;

    public AccountOnboardingValidator(AccountRepository accountRepository) {
        super(null);  // No need for a ValidationService since we're using a repository
        this.accountRepository = accountRepository;
    }

    @Override
    protected boolean handleRequest(UserOnboardingRequest request) {
        String accountNumber = request.getAccountNumber();

        // Check if account number matches the correct format
        if (!accountNumber.matches("\\d{10}")) {
            System.out.println("Invalid Account Number Format.");
            return false;
        }

        // Check if account exists in repository
        if (!accountRepository.accountExists(accountNumber)) {
            System.out.println("Account does not exist. Please enter a valid account number.");
            return false;
        }

        System.out.println("Account validation passed.");
        return true;
    }
}
