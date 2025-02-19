package com.serendib.services.onboarding.validators;

import com.serendib.models.UserOnboardingRequest;
import com.serendib.models.IdType;

import com.serendib.services.validation.ValidationService;

public class NicOnboardingValidator extends OnboardingValidator {
    public NicOnboardingValidator(ValidationService validator) {
        super(validator);
    }

    @Override
    protected boolean handleRequest(UserOnboardingRequest request) {
        if (request.getIdType() == IdType.NIC && !validator.validate(request.getIdNumber())) {
            System.out.println("Please enter a correct NIC number.");
            return false;
        }
        System.out.println("NIC validation passed.");
        return true;
    }
}
