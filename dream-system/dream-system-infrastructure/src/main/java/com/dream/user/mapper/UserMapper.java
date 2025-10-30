package com.dream.user.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.dept.DO.DeptDO;
import com.dream.user.DO.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<AdminUserDO> {

    default AdminUserDO selectListById(Long id) {
        return selectById(id);
    }

    default Long createUser(AdminUserDO user) {
        return (long) insert(user);
    }

}
