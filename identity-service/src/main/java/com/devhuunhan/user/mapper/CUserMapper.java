package com.devhuunhan.user.mapper;

import com.devhuunhan.user.dto.CUserDTO;
import com.devhuunhan.user.dto.UserProfileDTO;
import com.devhuunhan.user.model.CUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CUserMapper {
    CUserMapper INSTANCE = Mappers.getMapper(CUserMapper.class);
    CUserDTO toDTO(CUser entity);
    CUser toEntity(CUserDTO dto);
    UserProfileDTO toUserProfileDTO(CUser user);
}
