package com.dream.system.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.system.user.DO.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<AdminUserDO> {

    default AdminUserDO selectListById(Long id) {
        return selectById(id);
    }

    default Long createUser(AdminUserDO user) {
        return (long) insert(user);
    }

}
