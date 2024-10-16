package com.api_gateway.security.config;

import com.api_gateway.security.repository.IdentityClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
    IdentityClient identityClient(){
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/identity")
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(client)).build();

        return httpServiceProxyFactory.createClient(IdentityClient.class);
    }
}
