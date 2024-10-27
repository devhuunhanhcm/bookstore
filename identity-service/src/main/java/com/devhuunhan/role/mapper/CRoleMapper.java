package com.devhuunhan.role.mapper;

import com.devhuunhan.role.dto.CRoleDTO;
import com.devhuunhan.role.model.CRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CRoleMapper {
    CRoleMapper INSTANCE = Mappers.getMapper(CRoleMapper.class);
    CRoleDTO toDTO(CRole entity);
    CRole toEntity(CRoleDTO dto);
}
