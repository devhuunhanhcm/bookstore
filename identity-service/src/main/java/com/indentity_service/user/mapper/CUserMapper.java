package com.indentity_service.user.mapper;

import com.indentity_service.user.dto.CUserDTO;
import com.indentity_service.user.dto.UserProfileDTO;
import com.indentity_service.user.model.CUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CUserMapper {
    CUserMapper INSTANCE = Mappers.getMapper(CUserMapper.class);
    CUserDTO toDTO(CUser entity);
    CUser toEntity(CUserDTO dto);
    UserProfileDTO toUserProfileDTO(CUser user);
}
