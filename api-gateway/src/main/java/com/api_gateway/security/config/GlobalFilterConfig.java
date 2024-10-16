package com.api_gateway.security.config;

import com.api_gateway.helper.ResponseHelper;
import com.api_gateway.security.dto.TokenDTO;
import com.api_gateway.security.service.IdentityService;
import com.api_gateway.security.validator.RouterValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class GlobalFilterConfig implements GlobalFilter, Ordered {
     RouterValidator routerValidator;
     ObjectMapper objectMapper;
     IdentityService identityService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global filter enter....");
        ServerHttpRequest request = exchange.getRequest();
        //Check path
        if(routerValidator.isSecured.test(request)) {
            //check authorization header
            List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            if(CollectionUtils.isEmpty(authHeader))
                return unauthenticated(exchange.getResponse());

            //get token
            String token = authHeader.get(0).replace("Bearer ", "");

            return identityService.verifyToken(token).flatMap(res -> {
                if (res.getBody().isValid())
                    return chain.filter(exchange);
                else
                    return unauthenticated(exchange.getResponse());
            }).onErrorResume(throwable -> unauthenticated(exchange.getResponse()));

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unauthenticated(ServerHttpResponse response){
        String body;
        try {
            body = objectMapper.writeValueAsString(ResponseHelper.getErrorResponse("Unauthenticated",HttpStatus.UNAUTHORIZED));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
