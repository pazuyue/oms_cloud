package com.saas.common.security.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        System.out.println("loadUserByUsername:"+sysUser.toString());
        if (ObjectUtils.isEmpty(sysUser)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //查询用户信息

        //把数据封装到UserDetails
        return new LoginUser(sysUser);
    }
}
