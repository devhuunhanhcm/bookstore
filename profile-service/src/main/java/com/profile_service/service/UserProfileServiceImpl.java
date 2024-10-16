package com.profile_service.service;

import com.profile_service.dto.UserProfileDTO;
import com.profile_service.dto.UserProfileUpdateDTO;
import com.profile_service.mapper.UserProfileMapper;
import com.profile_service.model.UserProfile;
import com.profile_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = UserProfileMapper.INSTANCE.toEntity(userProfileDTO);
        userProfile = userProfileRepository.save(userProfile);

        return UserProfileMapper.INSTANCE.toDto(userProfile);
    }

    @Override
    public UserProfileDTO getUserProfileById(String id) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findById(id);
        if (userProfileOpt.isPresent()) {
            return UserProfileMapper.INSTANCE.toDto(userProfileOpt.get());
        }
        return null;
    }

    @Override
    public UserProfileDTO updateUserProfile(String id, UserProfileUpdateDTO userProfileDTO) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findById(id);
        if (userProfileOpt.isEmpty())
            throw new RuntimeException("User profile not found");

        UserProfile userProfile = userProfileOpt.get();
        String username = userProfileDTO.getUsername();
        String email = userProfileDTO.getEmail();
        String phone = userProfileDTO.getPhone();

        if(!username.equals(userProfile.getUsername())){
            if(userProfileRepository.findByUsername(username).isPresent())
                throw new RuntimeException("Username already exists");
        }
        if(!email.equals(userProfile.getEmail())){
            if(userProfileRepository.findByEmail(email).isPresent())
                throw new RuntimeException("Email already in use.");
        }
        if(!phone.equals(userProfile.getPhone())){
            if(userProfileRepository.findByPhone(phone).isPresent())
                throw new RuntimeException("Phone already in use.");
        }

        UserProfile userProfileUpdated = UserProfileMapper.INSTANCE.updateEntity(userProfileDTO,userProfile);
        userProfileRepository.save(userProfileUpdated);

        return UserProfileMapper.INSTANCE.toDto(userProfileUpdated);
    }

    @Override
    public List<UserProfileDTO> getAllUser() {
        return userProfileRepository.findAll()
                                    .stream()
                                    .map(userProfile -> UserProfileMapper.INSTANCE.toDto(userProfile))
                                    .collect(Collectors.toList());
    }
}
