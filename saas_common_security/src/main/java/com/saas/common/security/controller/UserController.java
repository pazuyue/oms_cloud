package com.saas.common.security.controller;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader("token") String token){
        if (!StringUtils.hasText(token)){
            return Result.failed("未登录，请先登录");
        }
        Map<String, Object> map = jwtUtils.getPayLoadALSOExcludeExpAndIat(token);
        String userId =map.get("user_id").toString();
        String redisKey = "login:"+userId;
        SysUser sysUser = redisCache.getCacheObject(redisKey);
        if (ObjectUtils.isEmpty(sysUser)){
            return Result.failed("未登录，请先登录");
        }
        return Result.success(sysUser);
    }
}
