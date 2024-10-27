package com.devhuunhan.security.service;

import com.devhuunhan.security.dto.TokenDTO;
import com.devhuunhan.security.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ResponseEntity<TokenDTO>> verifyToken(String token) {
        return identityClient.verifyToken(TokenDTO.builder().token(token).build());
    }
}
