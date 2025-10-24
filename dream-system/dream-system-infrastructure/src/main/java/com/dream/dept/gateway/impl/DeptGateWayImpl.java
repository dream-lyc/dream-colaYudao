package com.dream.dept.gateway.impl;

import com.dream.dept.DO.DeptDO;
import com.dream.dept.convert.DeptConvert;
import com.dream.dept.gateway.DeptGateWay;
import com.dream.dept.mapper.DeptMapper;
import com.dream.dept.model.Dept;
import jakarta.annotation.Resource;
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
}