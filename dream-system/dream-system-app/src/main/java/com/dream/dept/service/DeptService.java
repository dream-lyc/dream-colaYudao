package com.dream.dept.service;

import com.dream.dept.DTO.DeptDTO;
import com.dream.dept.convert.AppDeptConvert;
import com.dream.dept.gateway.DeptGateWay;
import com.dream.dept.model.Dept;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    private DeptGateWay deptGateWay;
    @Resource
    private AppDeptConvert appDeptConvert;


    public DeptDTO getDept(Long id) {
        Dept dept = deptGateWay.getDept(id);
        return appDeptConvert.convert2DTO(dept);
    }

    public Long createDept(DeptDTO deptDTO) {
        Dept dept = appDeptConvert.convert2Entity(deptDTO);
        return deptGateWay.createDept(dept);
    }
}
