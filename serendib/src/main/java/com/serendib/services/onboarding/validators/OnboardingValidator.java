// Chain of responsibility pattern to validate user onboarding requests

package com.serendib.services.onboarding.validators;

import com.serendib.models.UserOnboardingRequest;
import com.serendib.services.validation.ValidationService;

public abstract class OnboardingValidator {
    protected OnboardingValidator nextValidator;
    protected ValidationService validator;  // Reference to existing validation logic

    public OnboardingValidator(ValidationService validator) {
        this.validator = validator;
    }

    public void setNextValidator(OnboardingValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    public void validate(UserOnboardingRequest request) {
        if (!handleRequest(request)) {
            return;  // Stop chain if validation fails
        }
        if (nextValidator != null) {
            nextValidator.validate(request);
        }
    }

    protected abstract boolean handleRequest(UserOnboardingRequest request);
}
