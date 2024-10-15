package com.indentity_oauth2.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerUI {
    private String devUrl = "http://localhost:8080";

    final String securitySchemeName = "Authorization";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);

        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("devhuunhan@gmail.com");
        contact.setName("devhuunhan");
        contact.setUrl("https://www.devhuunhan.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info().title("Tutorial Management API").version("1.0").contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://www.devhuunhan.com/terms").license(mitLicense);

        return new OpenAPI().info(info)
                .servers(List.of(devServer))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer").bearerFormat("JWT")));
    }
}
