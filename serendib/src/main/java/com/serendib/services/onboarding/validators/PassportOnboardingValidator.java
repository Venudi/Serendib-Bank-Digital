package com.serendib.services.onboarding.validators;

import com.serendib.models.IdType;
import com.serendib.models.UserOnboardingRequest;
import com.serendib.services.validation.ValidationService;

public class PassportOnboardingValidator extends OnboardingValidator {
    public PassportOnboardingValidator(ValidationService validator) {
        super(validator);
    }

    @Override
    protected boolean handleRequest(UserOnboardingRequest request) {
        if (request.getIdType() == IdType.PASSPORT && !validator.validate(request.getIdNumber())) {
            System.out.println("Passport validation failed.");
            return false;
        }
        System.out.println("Passport validation passed.");
        return true;
    }   
}
