package com.indentity_oauth2.user.mapper;

import com.indentity_oauth2.user.dto.CUserDTO;
import com.indentity_oauth2.user.dto.UserProfileDTO;
import com.indentity_oauth2.user.model.CUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CUserMapper {
    CUserMapper INSTANCE = Mappers.getMapper(CUserMapper.class);
    CUserDTO toDTO(CUser entity);
    CUser toEntity(CUserDTO dto);
    UserProfileDTO toUserProfileDTO(CUser user);
}
