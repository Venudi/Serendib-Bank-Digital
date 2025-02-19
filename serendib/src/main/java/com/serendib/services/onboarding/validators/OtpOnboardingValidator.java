package com.serendib.services.onboarding.validators;

import com.serendib.models.UserOnboardingRequest;
import com.serendib.services.validation.ValidationService;

public class OtpOnboardingValidator extends OnboardingValidator {
    public OtpOnboardingValidator(ValidationService otpValidator) {
        super(otpValidator);
    }

    @Override
    protected boolean handleRequest(UserOnboardingRequest request) {
        if (!validator.validate(request.getOtp())) {
            System.out.println("OTP validation failed.");
            return false;
        }
        System.out.println("OTP validation passed.");
        return true;
    }
}
