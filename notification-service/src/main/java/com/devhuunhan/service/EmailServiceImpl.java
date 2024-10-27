package com.devhuunhan.service;

import com.devhuunhan.dto.EmailRequestDto;
import com.devhuunhan.dto.EmailResponseDto;
import com.devhuunhan.dto.SendEmailRequestDto;
import com.devhuunhan.dto.SenderDto;
import com.devhuunhan.repository.httpClient.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailClient emailClient;

    String apiKey = "";

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
