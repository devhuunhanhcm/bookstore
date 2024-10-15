package com.indentity_oauth2.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class CUserDTO {
    private String id;
    private String username;
    private String email;
    private boolean isActive;
}