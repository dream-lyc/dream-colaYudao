package com.dream.user.gateway;

import com.dream.dept.model.Dept;
import com.dream.user.model.AdminUser;


public interface UserGateWay {

    AdminUser getUser(Long id);

    AdminUser getUserByName(String name);

    Long createUser(AdminUser user);
}
