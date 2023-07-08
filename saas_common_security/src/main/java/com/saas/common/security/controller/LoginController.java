package com.saas.common.security.controller;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody SysUser user){
        System.out.println(user.toString());
        return loginService.login(user);
    }

    @PostMapping("/logout")
    @ResponseBody
    public Result logout(){
        return loginService.logout();
    }
}
