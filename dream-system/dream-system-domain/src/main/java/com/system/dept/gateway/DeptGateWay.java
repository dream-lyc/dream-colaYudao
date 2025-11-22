package com.system.dept.gateway;

import com.system.dept.model.Dept;



public interface DeptGateWay {

    Dept getDept(Long id);

    Long createDept(Dept dept);
}
