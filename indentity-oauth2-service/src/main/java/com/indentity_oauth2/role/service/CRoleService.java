package com.indentity_oauth2.role.service;


import com.indentity_oauth2.role.dto.CRoleDTO;
import com.indentity_oauth2.role.model.CRole;

import java.util.List;

public interface CRoleService {
    List<CRole> findAll();
    CRoleDTO save(CRoleDTO role);
    boolean removeRole(String roleId);
    CRoleDTO updateRole(String roleId, CRoleDTO dto);
}
