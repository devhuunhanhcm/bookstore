package com.indentity_oauth2.security.service;

import com.indentity_oauth2.security.dto.LoginDTO;
import com.indentity_oauth2.security.dto.RegisterDTO;
import com.indentity_oauth2.user.dto.TokenDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    TokenDTO login(LoginDTO dto, HttpServletResponse response);
    void register(RegisterDTO dto);
    void logout(String refreshToken);
}
