package com.indentity_service.security.service;

import com.indentity_service.common.config.AuthenticationRequestInterceptor;
import com.indentity_service.user.dto.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userProfileClient",url = "${api.service.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface UserProfileFeignClient {
    @PostMapping
    Object createProfile(@RequestBody UserProfileDTO dto);
}
