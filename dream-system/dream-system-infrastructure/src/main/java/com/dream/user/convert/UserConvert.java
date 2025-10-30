package com.dream.user.convert;

import com.dream.user.DO.AdminUserDO;
import com.dream.user.model.AdminUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper(componentModel = "spring")
public interface UserConvert {

   AdminUser convert2Entity(AdminUserDO  DO);

   AdminUserDO convert2DO(AdminUser user);

}
