package com.profile_service.annotation;

import com.profile_service.model.UserProfile;
import com.profile_service.repository.UserProfileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private String message;
    private final UserProfileRepository userProfileRepository;

    @Override
    public void initialize(UniqueUsername username) {
        message = username.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<UserProfile> userOpt = userProfileRepository.findByUsername(username);
        if(userOpt.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
