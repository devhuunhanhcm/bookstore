package com.indentity_oauth2.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class LoginDTO {
    @NotBlank
    @Size(min=6,max = 32,message = "{user.username.size}")
    private String username;
    @NotBlank
    @Size(min=8,max = 32,message = "{user.password.size}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long, contain at least one uppercase letter, one special character, and one number")
    private String password;
}
