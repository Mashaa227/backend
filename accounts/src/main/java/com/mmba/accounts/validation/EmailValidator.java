package com.mmba.accounts.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Basic email regex validation
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            return false;
        }

        // Advanced validation: Example - Ensure the domain is not blocked
        String[] blockedDomains = { "example.com", "test.com" };
        String domain = email.substring(email.lastIndexOf("@") + 1);
        for (String blocked : blockedDomains) {
            if (domain.equalsIgnoreCase(blocked)) {
                return false;
            }
        }

        return true;
    }
}
