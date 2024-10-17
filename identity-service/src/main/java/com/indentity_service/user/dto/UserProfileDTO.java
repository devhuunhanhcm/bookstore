package com.indentity_service.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class UserProfileDTO {
    private String userId;
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String phone;
}
