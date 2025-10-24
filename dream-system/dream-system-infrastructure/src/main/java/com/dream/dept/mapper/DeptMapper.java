package com.dream.dept.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.dept.DO.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {


//    default List<DeptDO> selectList(DeptListReqVO reqVO) {
//        return selectList(new LambdaQueryWrapper<DeptDO>()
//                .like(DeptDO::getName, "")
//                .eqIfPresent(DeptDO::getStatus, reqVO.getStatus()));
//    }

    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        LambdaQueryWrapper<DeptDO> queryWrapper = new LambdaQueryWrapper<DeptDO>()
                .eq(DeptDO::getParentId, parentId)
                .eq(DeptDO::getName, name);
        return selectOne(queryWrapper);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(new LambdaQueryWrapper<DeptDO>().eq(DeptDO::getParentId, parentId));
    }

    default List<DeptDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(new LambdaQueryWrapper<DeptDO>().in(DeptDO::getParentId, parentIds));
    }

    default List<DeptDO> selectListByLeaderUserId(Long id) {
        return selectList(new LambdaQueryWrapper<DeptDO>().in(DeptDO::getLeaderUserId, id));
    }

}
