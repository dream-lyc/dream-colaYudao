package com.system.user.gateway;

import com.system.user.model.AdminUser;


public interface UserGateWay {

    AdminUser getUser(Long id);

    AdminUser getUserByName(String name);

    Long createUser(AdminUser user);
}
