package com.dream.system.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.system.user.DO.AdminUserDO;
import com.dream.system.user.convert.UserConvert;
import com.system.user.gateway.UserGateWay;
import com.dream.system.user.mapper.UserMapper;
import com.system.user.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGateWayImpl implements UserGateWay {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserConvert userConvert;

    @Override
    public AdminUser getUser(Long id) {
        AdminUserDO adminUserDO = userMapper.selectById(id);
        return userConvert.convert2Entity(adminUserDO);
    }

    @Override
    public AdminUser getUserByName(String name) {
        AdminUserDO adminUserDO = userMapper.selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getUsername, name));
        return userConvert.convert2Entity(adminUserDO);
    }

    @Override
    public Long createUser(AdminUser user) {
        AdminUserDO adminUserDO = userConvert.convert2DO(user);
        userMapper.insert(adminUserDO);
        return adminUserDO.getId();
    }
}