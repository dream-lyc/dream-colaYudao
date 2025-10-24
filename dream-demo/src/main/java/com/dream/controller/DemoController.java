package com.dream.controller;

import com.dream.domain.TestADO;
import com.dream.mapper.TestAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo")
public class DemoController {
    @Autowired
    private TestAMapper testAMapper;

    @GetMapping("/test")
    public String test() {
        TestADO testADO = testAMapper.selectById(1);
        return "hello world";
    }
}
