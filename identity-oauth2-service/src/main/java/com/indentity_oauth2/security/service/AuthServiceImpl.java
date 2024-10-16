package com.indentity_oauth2.security.service;


import com.indentity_oauth2.role.model.CRole;
import com.indentity_oauth2.role.repository.CRoleRepository;
import com.indentity_oauth2.security.dto.LoginDTO;
import com.indentity_oauth2.security.dto.RegisterDTO;
import com.indentity_oauth2.security.jwt.JwtHelper;
import com.indentity_oauth2.security.model.RefreshToken;
import com.indentity_oauth2.security.repository.RefreshTokenRepository;
import com.indentity_oauth2.user.dto.TokenDTO;
import com.indentity_oauth2.user.dto.UserProfileDTO;
import com.indentity_oauth2.user.feignclient.UserProfileFeignClient;
import com.indentity_oauth2.user.mapper.CUserMapper;
import com.indentity_oauth2.user.model.CUser;
import com.indentity_oauth2.user.repository.CUserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private CUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CRoleRepository roleRepository;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserProfileFeignClient  userProfileFeignClient;

    @Override
    public TokenDTO login(LoginDTO dto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth;
        try{
            auth = authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            return null;
        }
        if(auth == null) return null;

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
        String token = jwtHelper.generateTokenWithUsernameAndRoles(dto.getUsername(), authorities);
        if(token == null) return null;

        Optional<CUser> userOpt = userRepository.findByUsername(dto.getUsername());

        if(userOpt.isEmpty())
            return null;
        CUser user = userOpt.get();

        //Set Refresh token for client
        String refreshTokenString = jwtHelper.generateRefreshTokenWithUsernameAndRoles(user.getUsername(),authorities);
        RefreshToken refreshTokenEntity = RefreshToken.builder().refreshToken(refreshTokenString).user(user).build();
        refreshTokenRepository.save(refreshTokenEntity);

        ResponseCookie cookie = ResponseCookie.from("refreshToken",refreshTokenString)
                .httpOnly(true)
                .secure(true)
                .maxAge(30 * 24 * 60 * 60 ) /*30 days*/
                .sameSite("NONE")
                .path("/")
                .domain("localhost")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return TokenDTO.builder().token(token).isValid(true).build();
    }

    @Override
    public void register(RegisterDTO dto) {
        Optional<CRole> defaultRole = roleRepository.findByCode("ROLE_USER");
        if(defaultRole.isEmpty()) throw new RuntimeException("Role not found");

        Set<CRole> roles = new LinkedHashSet<CRole>();
        roles.add(defaultRole.get());

        try{
            CUser user = CUser.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .email(dto.getEmail())
                    .isActive(true)
                    .roles(roles)
                    .build();
            user = userRepository.save(user);
            UserProfileDTO userProfileDTO = CUserMapper.INSTANCE.toUserProfileDTO(user);
            userProfileDTO.setUserId(user.getId());
            Object res = userProfileFeignClient.createProfile(userProfileDTO);

        } catch (Exception e) {
            throw new RuntimeException("Something wrong please try again");
        }
    }

    @Override
    public void logout(String refreshToken) {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByRefreshToken(refreshToken);
        if(refreshTokenOpt.isEmpty())
            return;
        try{
            refreshTokenRepository.delete(refreshTokenOpt.get());
        }catch(Exception e){
            throw new RuntimeException("Something wrong please try again");
        }
    }

    @Override
    public TokenDTO verifyToken(TokenDTO tokenDTO) {
        return TokenDTO.builder().token(tokenDTO.getToken()).isValid(jwtHelper.validationJwt(tokenDTO.getToken())).build();
    }
}
