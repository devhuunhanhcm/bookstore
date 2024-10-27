package com.devhuunhan.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientDto {
    private String name;
    private String email;
}
