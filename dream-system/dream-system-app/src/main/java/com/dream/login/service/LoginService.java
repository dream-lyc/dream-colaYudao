package com.dream.login.service;

import com.dream.common.enums.CommonStatusEnum;
import com.dream.login.DTO.LoginDTO;
import com.dream.login.convert.LoginConvert;
import com.dream.oauth2.model.OAuth2AccessToken;
import com.dream.oauth2.service.OAuth2TokenService;
import com.dream.user.enums.UserTypeEnum;
import com.dream.user.gateway.UserGateWay;
import com.dream.user.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserGateWay userGateWay;
    @Autowired
    OAuth2TokenService oauth2TokenService;
    @Autowired
    LoginConvert loginConvert;
    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginDTO login(String name, String password) {
        //认证
        AdminUser user = authenticate(name, password);
        //获取token
        LoginDTO loginDTO = createTokenAfterLoginSuccess(user.getId(), user.getUsername());
        return loginDTO;
    }

    private AdminUser authenticate(String username, String password) {
        // 校验账号是否存在
        AdminUser user = userGateWay.getUserByName(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!isPasswordMatch(password, user.getPassword())) {
            throw new RuntimeException("密码不正确");
        }
        // 校验是否禁用
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            throw new RuntimeException("用户被禁用");
        }
        return user;
    }

    private Boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private LoginDTO createTokenAfterLoginSuccess(Long userId, String username) {
        // 创建访问令牌
        OAuth2AccessToken accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                "default", null);
        // 构建返回结果
        return loginConvert.convert2LoginDTO(accessTokenDO);
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

}
