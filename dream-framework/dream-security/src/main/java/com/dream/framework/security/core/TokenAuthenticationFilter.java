package com.dream.framework.security.core;

import com.dream.framework.common.biz.system.oauth2.OAuth2TokenCommonApi;
import com.dream.framework.common.biz.system.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private OAuth2TokenCommonApi  oAuth2TokenCommonApi;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, OAuth2TokenCommonApi oAuth2TokenCommonApi) {
        // 匹配所有路径，方法不限
        super("/**");
        this.oAuth2TokenCommonApi = oAuth2TokenCommonApi;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //0. 提取 user，如 gateway 或其他服务传入 user，直接用
        //1. 提取 token
        String token = getToken(request);
        //2. 查 token，获取 user
        LoginUser loginUser = getUserByToken(token);
        //3.设置当前用户
        List<GrantedAuthority> authorities = loginUser.getScopes().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // 加ROLE_前缀
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        return token.substring(7).trim();
    }

    private LoginUser getUserByToken(String token) {
        OAuth2AccessTokenCheckRespDTO oAuth2AccessTokenCheckRespDTO = oAuth2TokenCommonApi.checkAccessToken(token);
        if (oAuth2AccessTokenCheckRespDTO == null) {
            return null;
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setId(oAuth2AccessTokenCheckRespDTO.getUserId());
        loginUser.setUserType(oAuth2AccessTokenCheckRespDTO.getUserType());
        loginUser.setInfo(oAuth2AccessTokenCheckRespDTO.getUserInfo());
        loginUser.setTenantId(oAuth2AccessTokenCheckRespDTO.getTenantId());
        loginUser.setScopes(oAuth2AccessTokenCheckRespDTO.getScopes());
        loginUser.setExpiresTime(oAuth2AccessTokenCheckRespDTO.getExpiresTime());
        
        return loginUser;
    }


}
