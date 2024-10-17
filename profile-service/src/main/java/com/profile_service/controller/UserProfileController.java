package com.profile_service.controller;

import com.profile_service.common.helper.ResponseHelper;
import com.profile_service.dto.UserProfileDTO;
import com.profile_service.dto.UserProfileUpdateDTO;
import com.profile_service.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public Object getAll(){
        List<UserProfileDTO> userProfileDTOList =  userProfileService.getAllUser();
        return ResponseHelper.getResponse(userProfileDTOList,HttpStatus.OK);
    }

    @PostMapping
    public Object createProfile(@Valid @RequestBody UserProfileDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);

        UserProfileDTO newDto = userProfileService.createUserProfile(dto);

        return ResponseHelper.getResponse(newDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Object getUserProfile(@PathVariable String id) {
        UserProfileDTO dto = userProfileService.getUserProfileById(id);
        if(dto == null)
            return ResponseHelper.getErrorResponse("UserProfile not found", HttpStatus.NOT_FOUND);
        return ResponseHelper.getResponse(dto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Object updateProfile(@PathVariable String id, @Valid @RequestBody UserProfileUpdateDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);

        UserProfileDTO newDto = userProfileService.updateUserProfile(id,dto);
        return ResponseHelper.getResponse(newDto,HttpStatus.OK);
    }
}
