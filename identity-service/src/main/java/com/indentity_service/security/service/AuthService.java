package com.indentity_service.security.service;

import com.indentity_service.security.dto.LoginDTO;
import com.indentity_service.security.dto.RegisterDTO;
import com.indentity_service.user.dto.TokenDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    TokenDTO login(LoginDTO dto, HttpServletResponse response);
    void register(RegisterDTO dto);
    void logout(String refreshToken);

    TokenDTO verifyToken(TokenDTO tokenDTO);
}
