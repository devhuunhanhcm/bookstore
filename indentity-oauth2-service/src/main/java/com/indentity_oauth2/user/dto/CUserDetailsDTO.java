package com.indentity_oauth2.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class CUserDetailsDTO {
    private String id;
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String phone;
    private boolean isActive;
    private String token;
}
