package com.indentity_service.role.controller;

import com.indentity_service.common.helper.ResponseHelper;
import com.indentity_service.role.dto.CRoleDTO;
import com.indentity_service.role.service.CRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/admin/role")
public class CRoleController {
    @Autowired
    private CRoleService service;

    @GetMapping()
    public Object findAll() {
        return ResponseHelper.getResponse(service.findAll(),HttpStatus.OK);
    }

    @PostMapping()
    public Object createRole(@Valid @RequestBody CRoleDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);

        return ResponseHelper.getResponse(service.save(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{role-id}")
    public Object removeRole(@PathVariable(name="role-id",required = true) String roleId) {
        boolean isRemoved = service.removeRole(roleId);
        if(!isRemoved)
            return ResponseHelper.getErrorResponse("Remove is failure.Role id is not existed.", HttpStatus.BAD_REQUEST);

        return ResponseHelper.getResponse("Remove role is successfully!!", HttpStatus.OK);
    }

    @PutMapping("/update/{role-id}")
    public Object updateRole(@PathVariable(name="role-id") String roleId,
                             @RequestBody CRoleDTO dto,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);

        CRoleDTO newDTO = service.updateRole(roleId, dto);
        if(newDTO == null)
            return ResponseHelper.getErrorResponse("Fail to update", HttpStatus.BAD_REQUEST);

        return ResponseHelper.getResponse(newDTO, HttpStatus.OK);
    }
}
