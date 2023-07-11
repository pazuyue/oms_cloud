package com.saas.common.security.service.impl.user;

import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.service.user.UserManageService;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserInfoByToken(String token) {
        Map<String, Object> map = jwtUtils.getPayLoadALSOExcludeExpAndIat(token);
        String userId =map.get("user_id").toString();
        String redisKey = "login:"+userId;
        return redisCache.getCacheObject(redisKey);
    }

    @Override
    public int register(SysUser sysUser) {
        return  sysUserMapper.insert(sysUser);
    }
}
