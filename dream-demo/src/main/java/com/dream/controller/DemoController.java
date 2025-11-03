package com.dream.controller;

import com.dream.domain.TestADO;
import com.dream.mapper.TestAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController("/demo")
public class DemoController {
    @Autowired
    private TestAMapper testAMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    public String test() {
        redisTemplate.opsForValue().set("test", "test");
        return redisTemplate.opsForValue().get("test").toString();
    }
}
