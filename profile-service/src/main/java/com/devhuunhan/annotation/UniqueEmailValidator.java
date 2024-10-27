package com.devhuunhan.annotation;

import com.devhuunhan.model.UserProfile;
import com.devhuunhan.repository.UserProfileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private String message;

    private final UserProfileRepository userProfileRepository;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
        message = uniqueEmail.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<UserProfile> userOpt = userProfileRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
