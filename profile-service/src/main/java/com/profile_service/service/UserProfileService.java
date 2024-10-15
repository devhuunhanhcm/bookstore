package com.profile_service.service;

import com.profile_service.dto.UserProfileDTO;
import com.profile_service.dto.UserProfileUpdateDTO;

public interface UserProfileService {
    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);
    UserProfileDTO getUserProfileById(String id);
    UserProfileDTO updateUserProfile(String id, UserProfileUpdateDTO userProfileDTO);
}
