package com.indentity_service.security.controller;

import com.indentity_service.common.helper.ResponseHelper;
import com.indentity_service.security.dto.LoginDTO;
import com.indentity_service.security.dto.RegisterDTO;
import com.indentity_service.security.service.AuthService;
import com.indentity_service.security.service.RefreshTokenService;
import com.indentity_service.user.dto.TokenDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult, HttpServletResponse response) {
        if(bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        TokenDTO dto = authService.login(loginDTO, response);
        if(dto == null)
            return ResponseHelper.getErrorResponse("Invalid username or password!", HttpStatus.UNAUTHORIZED);

        return ResponseHelper.getResponse(dto,HttpStatus.OK);
    }

    @PostMapping("/register")
    public Object register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        authService.register(registerDTO);
        return ResponseHelper.getResponse("Success", HttpStatus.OK);
    }
    @GetMapping("/get-new-token")
    public Object refreshToken(@CookieValue(name="refreshToken",required = false) String refreshToken,
                               HttpServletResponse response){
        String token = refreshTokenService.getToken(refreshToken);

        if(token == null){
            Cookie cookie = new Cookie("refreshToken",null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseHelper.getErrorResponse("Your session has expired.Please login again.",HttpStatus.UNAUTHORIZED);
        }
        return ResponseHelper.getResponse(token, HttpStatus.OK);
    }
    @PostMapping("/logout")
    public Object logout(@CookieValue(name="refreshToken",required = false) String refreshTokenCookie,
                         HttpServletResponse response,
                         HttpServletRequest request){
        String refreshToken = refreshTokenCookie;

        Cookie cookie = new Cookie("refreshToken",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        authService.logout(refreshToken);

        return ResponseHelper.getResponse("Logout successfully!!",HttpStatus.OK);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<TokenDTO> verifyToken(@RequestBody TokenDTO tokenDTO){
        TokenDTO resultDTO = authService.verifyToken(tokenDTO);
        if(!resultDTO.isValid())
            return new ResponseEntity<>(resultDTO,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resultDTO,HttpStatus.OK);
    }
}
