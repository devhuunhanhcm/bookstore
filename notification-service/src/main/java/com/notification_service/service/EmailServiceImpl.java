package com.notification_service.service;

import com.notification_service.dto.EmailRequestDto;
import com.notification_service.dto.EmailResponseDto;
import com.notification_service.dto.SendEmailRequestDto;
import com.notification_service.dto.SenderDto;
import com.notification_service.repository.httpClient.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailClient emailClient;

    String apiKey = "secret key";

    @Override
    public EmailResponseDto sendEmail(SendEmailRequestDto sendEmailRequestDto) {
        EmailRequestDto emailRequestDto = EmailRequestDto.builder()
                .sender(SenderDto.builder()
                        .name("devhuunhan")
                        .email("devhuunhan@gmail.com")
                        .build())
                .to(List.of(sendEmailRequestDto.getTo()))
                .subject(sendEmailRequestDto.getSubject())
                .htmlContent(sendEmailRequestDto.getHtmlContent())
                .build();

        try{
            return emailClient.sendMail(apiKey,emailRequestDto);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
