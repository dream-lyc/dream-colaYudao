package com.dream.system.user.convert;

import com.dream.system.user.DO.AdminUserDO;
import com.system.user.model.AdminUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserConvert {

   AdminUser convert2Entity(AdminUserDO  DO);

   AdminUserDO convert2DO(AdminUser user);

}
