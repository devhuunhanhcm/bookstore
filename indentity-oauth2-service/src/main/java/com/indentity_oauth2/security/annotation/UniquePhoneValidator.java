package com.indentity_oauth2.security.annotation;

import com.indentity_oauth2.user.model.CUser;
import com.indentity_oauth2.user.repository.CUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private String message;

    @Autowired
    private CUserRepository repository;

    @Override
    public void initialize(UniquePhone uniqueUsername) {
        message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        Optional<CUser> userOpt = repository.findByPhone(phone);
        if(userOpt.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
