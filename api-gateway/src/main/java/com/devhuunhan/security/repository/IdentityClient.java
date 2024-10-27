package com.devhuunhan.security.repository;

import com.devhuunhan.security.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/verify-token")
    Mono<ResponseEntity<TokenDTO>> verifyToken(@RequestBody TokenDTO tokenDTO);
}
