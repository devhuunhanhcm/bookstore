package com.devhuunhan.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequestDto {
    private SenderDto sender;
    private List<RecipientDto> to;
    private String htmlContent;
    private String subject;
}
