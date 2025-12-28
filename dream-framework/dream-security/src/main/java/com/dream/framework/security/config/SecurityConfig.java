package com.dream.framework.security.config;

import com.dream.framework.common.biz.system.oauth2.OAuth2TokenCommonApi;
import com.dream.framework.security.core.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public TokenAuthenticationFilter  tokenAuthenticationFilter(AuthenticationManager  authenticationManager, OAuth2TokenCommonApi oAuth2TokenCommonApi) {
        return new TokenAuthenticationFilter(authenticationManager, oAuth2TokenCommonApi);
    }

    /**
     * 定义安全规则（如 URL 访问权限、登录方式等）
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,TokenAuthenticationFilter filter) throws Exception {
//        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(authenticationManager,oAuth2TokenCommonApi);
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .build();
    }
    /**
     * 配置密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}