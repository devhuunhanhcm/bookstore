package com.indentity_oauth2.user.feignclient;

import com.indentity_oauth2.user.dto.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userProfileClient",url = "${api.service.profile}")
public interface UserProfileFeignClient {
    @PostMapping
    Object createProfile(@RequestBody UserProfileDTO dto);
}
