package com.dream.system.system.web.controller;

import com.dream.system.login.DTO.LoginDTO;
import com.dream.system.login.service.LoginService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/login")
@Validated
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @PermitAll
    public LoginDTO login(@RequestBody LoginDTO loginDTO) {
        LoginDTO login = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return login;
    }
}
