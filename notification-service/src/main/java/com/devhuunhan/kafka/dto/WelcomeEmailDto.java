package com.devhuunhan.kafka.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WelcomeEmailDto {
    private String recipient;
    private String subject;
    private String body;
}
