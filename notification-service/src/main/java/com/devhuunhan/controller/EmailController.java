package com.devhuunhan.controller;

import com.devhuunhan.common.helper.ResponseHelper;
import com.devhuunhan.dto.EmailResponseDto;
import com.devhuunhan.dto.SendEmailRequestDto;
import com.devhuunhan.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
