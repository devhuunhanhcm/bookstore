package com.indentity_oauth2.user.mapper;

import com.indentity_oauth2.user.dto.CUserDTO;
import com.indentity_oauth2.user.dto.CUserDetailsDTO;
import com.indentity_oauth2.user.dto.CUserDetailsUpdateDTO;
import com.indentity_oauth2.user.model.CUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CUserMapper {
    CUserMapper INSTANCE = Mappers.getMapper(CUserMapper.class);
    @Mapping(target = "id",ignore = true)
    CUser updateToUserDetails(CUserDetailsUpdateDTO userDetails, @MappingTarget CUser user);
    CUserDTO toDTO(CUser entity);
    CUser toEntity(CUserDTO dto);
    CUserDetailsDTO toUserDetailsDTO(CUser entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "address", ignore = true)
    })
    CUser update(CUserDetailsDTO userDetails, @MappingTarget CUser user);
}
