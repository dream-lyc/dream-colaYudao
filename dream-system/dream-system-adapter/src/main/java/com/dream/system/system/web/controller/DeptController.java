package com.dream.system.system.web.controller;

import com.alibaba.cola.dto.SingleResponse;

import com.dream.system.dept.DTO.DeptDTO;
import com.dream.system.dept.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("detail")
    public SingleResponse<DeptDTO> getDept(@RequestParam("id") Long id) {
        DeptDTO dept = deptService.getDept(id);
        return SingleResponse.of(dept);

    }


}
