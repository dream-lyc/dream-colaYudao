package com.dream.login.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginDTO {

    private Long userId;

    private String username;

    private String password;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiresTime;
}
