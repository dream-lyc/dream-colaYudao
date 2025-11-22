package com.dream.system.dept.impl;

import com.dream.system.dept.DO.DeptDO;
import com.dream.system.dept.convert.DeptConvert;
import com.system.dept.gateway.DeptGateWay;
import com.dream.system.dept.mapper.DeptMapper;
import com.system.dept.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptGateWayImpl implements DeptGateWay {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private DeptConvert deptConvert;

    @Override
    public Dept getDept(Long id) {
        DeptDO deptDO = deptMapper.selectById(id);
        return deptConvert.convert2Entity(deptDO);
    }

    @Override
    public Long createDept(Dept dept) {
        DeptDO deptDO = deptConvert.convert2DO(dept);
        int id = deptMapper.insert(deptDO);
        return (long) id;
    }
}