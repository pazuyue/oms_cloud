package com.saas.common.security.controller;

import cn.hutool.core.util.NumberUtil;
import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.service.user.UserManageService;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userManage")
public class UserController {

    @Resource
   private UserManageService userManageService;

    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader("token") String token){
        if (!StringUtils.hasText(token)){
            return Result.failed("未登录，请先登录");
        }
        SysUser sysUser = userManageService.getUserInfoByToken(token);
        if (ObjectUtils.isEmpty(sysUser)){
            return Result.failed("未登录，请先登录");
        }
        return Result.success(sysUser);
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
