package com.dream.system.system.web.controller;

import com.alibaba.cola.dto.SingleResponse;
import com.dream.system.user.DTO.UserDTO;
import com.dream.system.user.service.UserService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    @PermitAll
    public SingleResponse<Long> createUser(@Validated @RequestBody UserDTO userDTO) {
        Long id = userService.createUser(userDTO);
        return SingleResponse.of(id);
    }
}
