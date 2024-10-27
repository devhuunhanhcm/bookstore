package com.devhuunhan.controller;

import com.devhuunhan.dto.EmailRequestDto;
import com.devhuunhan.dto.RecipientDto;
import com.devhuunhan.dto.SendEmailRequestDto;
import com.devhuunhan.kafka.dto.WelcomeEmailDto;
import com.devhuunhan.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationController {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "register-success")
    public void listenerRegisterEvent(WelcomeEmailDto message) {
        emailService.sendEmail(SendEmailRequestDto.builder()
                .htmlContent(message.getBody())
                .to(RecipientDto.builder()
                        .email(message.getRecipient())
                        .name(message.getSubject())
                        .build())
                .subject(message.getSubject())
                .build());
    }


}
