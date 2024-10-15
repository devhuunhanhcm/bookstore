package com.profile_service.dto;

import jakarta.validation.constraints.NotBlank;
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
public class UserProfileUpdateDTO {
    private String id;
    private String userId;
    @NotBlank
    private String username;
    @NotBlank
    private String displayName;
    private String email;
    private String avatar;
    private String address;
    private String phone;
}
