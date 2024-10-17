package com.notification_service.service;

import com.notification_service.dto.EmailResponseDto;
import com.notification_service.dto.SendEmailRequestDto;

public interface EmailService {
    EmailResponseDto sendEmail(SendEmailRequestDto emailRequestDto);
}
