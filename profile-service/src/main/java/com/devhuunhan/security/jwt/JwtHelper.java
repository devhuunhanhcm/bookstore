package com.devhuunhan.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtHelper {
    private static final String PREFIX = "Bearer ";

    @Value("${app.jwt.secretKey}")
    private String strKeys;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKeys));
    }

    public JwtClaimsDTO getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload();
        List<String> authoritiesString = claims.get("authorities", List.class);
        Collection<SimpleGrantedAuthority> authorities = authoritiesString.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return JwtClaimsDTO.builder().username(claims.getSubject()).authorities(authorities).build();
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt == null)
            return null;

        return jwt.substring(PREFIX.length(), jwt.length());
    }
}
