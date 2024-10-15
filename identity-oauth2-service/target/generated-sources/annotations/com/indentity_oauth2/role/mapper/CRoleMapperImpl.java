package com.indentity_oauth2.role.mapper;

import com.indentity_oauth2.role.dto.CRoleDTO;
import com.indentity_oauth2.role.dto.CRoleDTO.CRoleDTOBuilder;
import com.indentity_oauth2.role.model.CRole;
import com.indentity_oauth2.role.model.CRole.CRoleBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T19:59:07+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class CRoleMapperImpl implements CRoleMapper {

    @Override
    public CRoleDTO toDTO(CRole entity) {
        if ( entity == null ) {
            return null;
        }

        CRoleDTOBuilder<?, ?> cRoleDTO = CRoleDTO.builder();

        cRoleDTO.id( entity.getId() );
        cRoleDTO.code( entity.getCode() );
        cRoleDTO.description( entity.getDescription() );

        return cRoleDTO.build();
    }

    @Override
    public CRole toEntity(CRoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CRoleBuilder<?, ?> cRole = CRole.builder();

        cRole.id( dto.getId() );
        cRole.code( dto.getCode() );
        cRole.description( dto.getDescription() );

        return cRole.build();
    }
}
