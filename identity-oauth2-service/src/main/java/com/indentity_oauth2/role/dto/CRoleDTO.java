package com.indentity_oauth2.role.dto;

import com.indentity_oauth2.role.validation.UniqueCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class CRoleDTO {
    private String id;

    @NotBlank
    @Size(min = 6,max = 32,message = "{role.code.size}")
    @UniqueCode
    private String code;

    @NotBlank(message = "{role.description.notBlank}")
    private String description;
}