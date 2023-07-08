package com.saas.common.security.service.impl.user;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.service.user.LoginService;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public Result login(SysUser user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String user_id = loginUser.getSysUser().getId().toString();
        String jwt = jwtUtils.createJwt(user_id);
        redisCache.setCacheObject("login:"+user_id,loginUser.getSysUser(),3600, TimeUnit.SECONDS);
        return Result.success(jwt);
    }

    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser)authentication.getPrincipal();
        String redisKey = "login:"+sysUser.getId();
        redisCache.deleteObject(redisKey);
        return Result.success();
    }
}
