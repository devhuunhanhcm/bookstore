package com.indentity_service.common.auditoraware;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return Optional.ofNullable("");
        if(auth.getPrincipal() instanceof String)
            return Optional.ofNullable((String) auth.getPrincipal());

        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        return Optional.ofNullable(currentUser.getUsername());
    }
}
