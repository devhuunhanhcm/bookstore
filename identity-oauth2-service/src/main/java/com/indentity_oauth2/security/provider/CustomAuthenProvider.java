package com.indentity_oauth2.security.provider;

import com.indentity_oauth2.user.model.CUser;
import com.indentity_oauth2.user.repository.CUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<CUser> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty())
            return null;

        CUser user = userOpt.get();
        UsernamePasswordAuthenticationToken token = null;
        if(passwordEncoder.matches(password,user.getPassword())){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));

            token = new UsernamePasswordAuthenticationToken(username,"",authorities);
        }

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
