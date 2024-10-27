package com.devhuunhan.service;

import com.devhuunhan.dto.UserProfileDTO;
import com.devhuunhan.dto.UserProfileUpdateDTO;

import java.util.List;

public interface UserProfileService {
    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);
    UserProfileDTO getUserProfileById(String id);
    UserProfileDTO updateUserProfile(String id, UserProfileUpdateDTO userProfileDTO);
    List<UserProfileDTO> getAllUser();
}
