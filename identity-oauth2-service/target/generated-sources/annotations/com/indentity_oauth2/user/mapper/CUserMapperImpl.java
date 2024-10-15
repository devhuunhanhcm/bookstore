package com.indentity_oauth2.user.mapper;

import com.indentity_oauth2.user.dto.CUserDTO;
import com.indentity_oauth2.user.dto.CUserDTO.CUserDTOBuilder;
import com.indentity_oauth2.user.dto.UserProfileDTO;
import com.indentity_oauth2.user.dto.UserProfileDTO.UserProfileDTOBuilder;
import com.indentity_oauth2.user.model.CUser;
import com.indentity_oauth2.user.model.CUser.CUserBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T19:59:07+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class CUserMapperImpl implements CUserMapper {

    @Override
    public CUserDTO toDTO(CUser entity) {
        if ( entity == null ) {
            return null;
        }

        CUserDTOBuilder<?, ?> cUserDTO = CUserDTO.builder();

        cUserDTO.id( entity.getId() );
        cUserDTO.username( entity.getUsername() );
        cUserDTO.email( entity.getEmail() );

        return cUserDTO.build();
    }

    @Override
    public CUser toEntity(CUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CUserBuilder<?, ?> cUser = CUser.builder();

        cUser.id( dto.getId() );
        cUser.username( dto.getUsername() );
        cUser.email( dto.getEmail() );

        return cUser.build();
    }

    @Override
    public UserProfileDTO toUserProfileDTO(CUser user) {
        if ( user == null ) {
            return null;
        }

        UserProfileDTOBuilder<?, ?> userProfileDTO = UserProfileDTO.builder();

        userProfileDTO.username( user.getUsername() );
        userProfileDTO.email( user.getEmail() );

        return userProfileDTO.build();
    }
}
