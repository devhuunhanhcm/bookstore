package com.notification_service.security.jwt;


import com.notification_service.security.dto.UsernameAndRolesDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
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
    private static final long EXPIRE_DURATION = 1 * 60 * 60 * 1000;

    @Value("${app.jwt.secretKey}")
    private String strKeys;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKeys));
    }


    public Boolean validationJwt(String token) {
        if (token == null)
            return false;
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (UnsupportedJwtException ex1) {
            System.out.println("Jwt is not supported: " + ex1);
        } catch (MalformedJwtException ex2) {
            System.out.println("Invalid token: " + ex2);
        } catch (SignatureException ex3) {
            System.out.println("Invalid token: " + ex3);
        } catch (ExpiredJwtException ex4) {
            System.out.println("Jwt is expired" + ex4);
        } catch (IllegalArgumentException ex5) {
            System.out.println("Jwt claims is empty: " + ex5);
        }

        return false;
    }

    public UsernameAndRolesDTO getUsernameAndRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload();
        List<String> authoritiesString = claims.get("authorities", List.class);
        Collection<SimpleGrantedAuthority> authorities = authoritiesString.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UsernameAndRolesDTO.builder().username(claims.getSubject()).authorities(authorities).build();
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt == null)
            return null;

        return jwt.substring(PREFIX.length(), jwt.length());
    }
}
