package com.devhuunhan.role.service;


import com.devhuunhan.role.dto.CRoleDTO;
import com.devhuunhan.role.model.CRole;

import java.util.List;

public interface CRoleService {
    List<CRole> findAll();
    CRoleDTO save(CRoleDTO role);
    boolean removeRole(String roleId);
    CRoleDTO updateRole(String roleId, CRoleDTO dto);
}
