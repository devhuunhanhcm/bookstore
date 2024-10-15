package com.profile_service.dto;

import com.profile_service.annotation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserProfileDTO {
    private String id;
    private String userId;
    @UniqueUsername
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String address;
    private String phone;
}
