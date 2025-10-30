package com.dream.login.service;

import com.dream.common.enums.CommonStatusEnum;
import com.dream.login.DTO.LoginDTO;
import com.dream.user.gateway.UserGateWay;
import com.dream.user.model.AdminUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserGateWay userGateWay;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    public LoginDTO login(String name, String password) {
        AdminUser user = authenticate(name, password);
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(user, loginDTO);
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
//        return passwordEncoder.matches(rawPassword, encodedPassword);
        return true;
    }

}
