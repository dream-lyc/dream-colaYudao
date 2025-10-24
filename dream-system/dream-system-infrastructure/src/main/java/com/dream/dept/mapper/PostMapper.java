package com.dream.dept.mapper;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.dept.DO.PostDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<PostDO> {

    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        LambdaQueryWrapper<PostDO> wrapper = new LambdaQueryWrapper<>();
        if (ids != null) {
            wrapper.in(PostDO::getId, ids);
        }
        if (statuses != null) {
            wrapper.in(PostDO::getStatus, statuses);
        }
        return selectList(wrapper);
    }

//    default PageResponse<PostDO> selectPage(PostPageReqVO reqVO) {
//        LambdaQueryWrapper<PostDO> wrapper = new LambdaQueryWrapper<>();
//        // MyBatis Plus 查询
//        IPage<PostDO> page = new Page<>(1, 10);
//        IPage<PostDO> pageResult = selectPage(page, wrapper);
//        return MyBatisUtils.convertPage(pageResult);
//    }

}
