package com.profile_service.mapper;

import com.profile_service.dto.UserProfileDTO;
import com.profile_service.dto.UserProfileUpdateDTO;
import com.profile_service.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);
    UserProfile toEntity(UserProfileDTO userProfileDTO);
    UserProfileDTO toDto(UserProfile userProfile);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userId", ignore = true)
    })
    UserProfile updateEntity(UserProfileUpdateDTO userProfileDTO, @MappingTarget UserProfile userProfile);
}
