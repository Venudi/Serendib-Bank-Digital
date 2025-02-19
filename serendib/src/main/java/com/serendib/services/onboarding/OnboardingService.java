package com.serendib.services.onboarding;

import com.serendib.repository.AccountRepository;
import com.serendib.services.onboarding.validators.*;
import com.serendib.services.validation.ValidationService;
import com.serendib.models.UserOnboardingRequest;

public class OnboardingService {
    // chain of responsibility pattern to validate user onboarding requests
    private OnboardingValidator chain;

    public OnboardingService(ValidationService nicValidator, ValidationService passportValidator, ValidationService otpValidator, AccountRepository accountRepository) {
        // Build the validation chain
        OnboardingValidator nicValidatorNode = new NicOnboardingValidator(nicValidator);
        OnboardingValidator passportValidatorNode = new PassportOnboardingValidator(passportValidator);
        OnboardingValidator accountValidatorNode = new AccountOnboardingValidator(accountRepository);
        OnboardingValidator otpValidatorNode = new OtpOnboardingValidator(otpValidator);

        nicValidatorNode.setNextValidator(passportValidatorNode);
        passportValidatorNode.setNextValidator(accountValidatorNode);
        accountValidatorNode.setNextValidator(otpValidatorNode);

        this.chain = nicValidatorNode;
    }

    public boolean onboardUser(UserOnboardingRequest request) {
        System.out.println("Starting Onboarding Validation...");
        chain.validate(request);
        return true;
    }
}
