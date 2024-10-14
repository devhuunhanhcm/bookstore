package com.indentity_oauth2.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class CUserDetailsUpdateDTO {
    private String displayName;
    private String email;
    private String avatar;
    private String address;
    private String phone;
}