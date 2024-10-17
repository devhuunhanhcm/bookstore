package com.notification_service.repository.httpClient;

import com.notification_service.dto.EmailRequestDto;
import com.notification_service.dto.EmailResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "email-client",url = "https://api.brevo.com")
public interface EmailClient {
    @PostMapping(value = "/v3/smtp/email",produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponseDto sendMail(@RequestHeader("api-key") String apiKey, @RequestBody EmailRequestDto body);
}
