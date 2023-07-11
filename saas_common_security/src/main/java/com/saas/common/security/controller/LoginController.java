package com.saas.common.security.controller;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.service.user.LoginService;
import com.saas.common.security.service.user.UserManageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Resource
    private UserManageService userManageService;

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

    @PostMapping("/register")
    public Result register(@Validated SysUser sysUser){
        int id = userManageService.register(sysUser);
        if (id>0){
            return Result.success();
        }
        return Result.failed("注册失败");
    }
}
