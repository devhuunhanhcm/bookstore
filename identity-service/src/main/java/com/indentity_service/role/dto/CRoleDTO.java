package com.indentity_service.role.dto;

import com.indentity_service.role.validation.UniqueCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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