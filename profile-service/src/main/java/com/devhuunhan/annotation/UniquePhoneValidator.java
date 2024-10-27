package com.devhuunhan.annotation;

import com.devhuunhan.model.UserProfile;
import com.devhuunhan.repository.UserProfileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private String message;
    private final UserProfileRepository userProfileRepository;

    @Override
    public void initialize(UniquePhone uniqueUsername) {
        message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        Optional<UserProfile> userOpt = userProfileRepository.findByPhone(phone);
        if(userOpt.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
