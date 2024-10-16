package com.api_gateway.security.validator;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class RouterValidator {
    @Value("${app.api-prefix}")
    public String apiPrefix;
    public List<String> publicApiEndPoints;

    @PostConstruct
    public void init() {
        publicApiEndPoints = List.of(
                String.format("%s/identity/auth/login", apiPrefix),
                String.format("%s/identity/auth/register", apiPrefix),
                String.format("%s/identity/auth/logout", apiPrefix)
        );
    }

    public Predicate<ServerHttpRequest> isSecured = request -> publicApiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
