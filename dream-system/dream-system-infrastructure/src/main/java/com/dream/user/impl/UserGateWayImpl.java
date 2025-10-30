package com.dream.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.dept.DO.DeptDO;
import com.dream.dept.convert.DeptConvert;
import com.dream.dept.gateway.DeptGateWay;
import com.dream.dept.mapper.DeptMapper;
import com.dream.dept.model.Dept;
import com.dream.user.DO.AdminUserDO;
import com.dream.user.convert.UserConvert;
import com.dream.user.gateway.UserGateWay;
import com.dream.user.mapper.UserMapper;
import com.dream.user.model.AdminUser;
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
        int id = userMapper.insert(adminUserDO);
        return (long) id;
    }
}