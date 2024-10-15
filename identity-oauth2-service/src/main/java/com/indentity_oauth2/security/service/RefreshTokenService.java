package com.indentity_oauth2.security.service;

public interface RefreshTokenService {
    String getToken(String  refreshTokenString);
}
