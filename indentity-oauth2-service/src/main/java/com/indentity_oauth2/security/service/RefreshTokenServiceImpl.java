package com.indentity_oauth2.security.service;

import com.indentity_oauth2.role.model.CRole;
import com.indentity_oauth2.security.jwt.JwtHelper;
import com.indentity_oauth2.security.model.RefreshToken;
import com.indentity_oauth2.security.repository.RefreshTokenRepository;
import com.indentity_oauth2.user.model.CUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private RefreshTokenRepository repository;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String getToken(String refreshTokenString) {
        Optional<RefreshToken> refreshTokenOpt = repository.findByRefreshToken(refreshTokenString);

        if(refreshTokenOpt.isEmpty())
            return null;

        RefreshToken refreshToken = refreshTokenOpt.get();

        if(!jwtHelper.validationJwt(refreshToken.getRefreshToken())){
            repository.delete(refreshToken);
            return null;
        }
        CUser user = refreshToken.getUser();
        String token = null;
        Set<CRole> roles = user.getRoles();

        if(roles != null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            roles.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getCode())));
            token = jwtHelper.generateTokenWithUsernameAndRoles(user.getUsername(),authorities);
        }
        return token;
    }

    public void delete(String refreshToken) {
        Optional<RefreshToken> refreshTokenOpt = repository.findByRefreshToken(refreshToken);
        if(refreshTokenOpt.isEmpty())
            return;
        try{
            repository.delete(refreshTokenOpt.get());
        }catch(Exception e){
            return;
        }
    }
}
