package com.devhuunhan.security.service;

import com.devhuunhan.security.dto.LoginDTO;
import com.devhuunhan.security.dto.RegisterDTO;
import com.devhuunhan.user.dto.TokenDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    TokenDTO login(LoginDTO dto, HttpServletResponse response);
    void register(RegisterDTO dto);
    void logout(String refreshToken,String token);

    TokenDTO verifyToken(TokenDTO tokenDTO);
}
