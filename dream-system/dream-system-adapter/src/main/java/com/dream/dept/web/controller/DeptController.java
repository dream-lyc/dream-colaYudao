package com.dream.dept.web.controller;

import com.alibaba.cola.dto.SingleResponse;

import com.dream.dept.DTO.DeptDTO;
import com.dream.dept.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/system/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("create")
    public SingleResponse<Long> createDept(@RequestBody DeptDTO deptDTO) {
        Long id = deptService.createDept(deptDTO);
        return SingleResponse.of(id);
    }

    @PostMapping("detail")
    public SingleResponse<DeptDTO> getDept(@RequestBody Long id) {
        DeptDTO dept = deptService.getDept(id);
        return SingleResponse.of(dept);
    }


}
