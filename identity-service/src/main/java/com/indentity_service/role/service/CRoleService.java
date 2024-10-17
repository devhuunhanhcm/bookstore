package com.indentity_service.role.service;


import com.indentity_service.role.dto.CRoleDTO;
import com.indentity_service.role.model.CRole;

import java.util.List;

public interface CRoleService {
    List<CRole> findAll();
    CRoleDTO save(CRoleDTO role);
    boolean removeRole(String roleId);
    CRoleDTO updateRole(String roleId, CRoleDTO dto);
}
