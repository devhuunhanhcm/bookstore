package com.notification_service.controller;

import com.notification_service.common.helper.ResponseHelper;
import com.notification_service.dto.EmailRequestDto;
import com.notification_service.dto.EmailResponseDto;
import com.notification_service.dto.SendEmailRequestDto;
import com.notification_service.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("${app.api-prefix}/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public Object sendEmail(@RequestBody SendEmailRequestDto sendEmailRequestDto) {
        EmailResponseDto dto = emailService.sendEmail(sendEmailRequestDto);

        return ResponseHelper.getResponse(dto, HttpStatus.OK);
    }

    @KafkaListener(topics = "onboard-success")
    public void onboardNotification(String message) {
        log.info(message);
    }
}
