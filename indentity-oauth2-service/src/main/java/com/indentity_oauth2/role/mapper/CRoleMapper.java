package com.indentity_oauth2.role.mapper;

import com.indentity_oauth2.role.dto.CRoleDTO;
import com.indentity_oauth2.role.model.CRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CRoleMapper {
    CRoleMapper INSTANCE = Mappers.getMapper(CRoleMapper.class);
    CRoleDTO toDTO(CRole entity);
    CRole toEntity(CRoleDTO dto);
}
