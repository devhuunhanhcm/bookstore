package com.devhuunhan.service;

import com.devhuunhan.dto.EmailResponseDto;
import com.devhuunhan.dto.SendEmailRequestDto;

public interface EmailService {
    EmailResponseDto sendEmail(SendEmailRequestDto emailRequestDto);
}
