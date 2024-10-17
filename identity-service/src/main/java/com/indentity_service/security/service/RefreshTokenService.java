package com.indentity_service.security.service;

public interface RefreshTokenService {
    String getToken(String  refreshTokenString);
}
