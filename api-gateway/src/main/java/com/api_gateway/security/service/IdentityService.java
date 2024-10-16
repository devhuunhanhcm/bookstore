package com.api_gateway.security.service;

import com.api_gateway.helper.ApiResponse;
import com.api_gateway.security.dto.TokenDTO;
import com.api_gateway.security.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
