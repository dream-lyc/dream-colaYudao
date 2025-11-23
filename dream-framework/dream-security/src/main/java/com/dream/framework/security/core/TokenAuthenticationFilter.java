package com.dream.framework.security.core;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;



public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        // 匹配所有路径，方法不限
        super("/**");
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //0. 提取 user，如 gateway 或其他服务传入 user，直接用
        //1. 提取 token
        String token = getToken(request);
        //2. 查 token，获取 user
        LoginUser loginUser = gerUserByToken(token);
        //3.设置当前用户
        return new UsernamePasswordAuthenticationToken(loginUser, null, null);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        return token.substring(7).trim();
    }

    private LoginUser gerUserByToken(String token) {
//        OAuth2AccessTokenCheckRespDTO oAuth2AccessTokenCheckRespDTO = oauth2TokenApi.checkAccessToken(token);
//        if (oAuth2AccessTokenCheckRespDTO == null) {
//            return null;
//        }
        return new LoginUser();
    }


}
