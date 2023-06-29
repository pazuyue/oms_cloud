package com.saas.common.security.controller;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody SysUser user){
        return loginService.login(user);
    }
}
