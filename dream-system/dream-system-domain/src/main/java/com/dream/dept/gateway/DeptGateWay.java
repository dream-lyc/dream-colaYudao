package com.dream.dept.gateway;

import com.dream.dept.model.Dept;


public interface DeptGateWay {

    Dept getDept(Long id);

    Long createDept(Dept dept);
}
