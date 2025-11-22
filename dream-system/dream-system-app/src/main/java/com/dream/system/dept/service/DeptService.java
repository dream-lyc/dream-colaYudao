package com.dream.system.dept.service;

import com.dream.system.dept.DTO.DeptDTO;
import com.dream.system.dept.convert.AppDeptConvert;
import com.system.dept.gateway.DeptGateWay;
import com.system.dept.model.Dept;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Resource
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
