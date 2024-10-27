package com.devhuunhan.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendEmailRequestDto {
    private RecipientDto to;
    private String subject;
    private String htmlContent;
}
