package com.indentity_oauth2.security.jwt;

import com.indentity_oauth2.security.dto.UsernameAndRolesDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenrizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper helper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        String token = helper.getToken(request);
        try{
            if(helper.validationJwt(token)){
                UsernameAndRolesDTO currentUser = helper.getUsernameAndRoleFromToken(token);
                Authentication auth = new UsernamePasswordAuthenticationToken(currentUser.getUsername(),"",currentUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception ex){
            response.setHeader("Error",ex.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        filterChain.doFilter(request, response);
    }
}
