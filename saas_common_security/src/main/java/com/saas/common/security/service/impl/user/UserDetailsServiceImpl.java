package com.saas.common.security.service.impl.user;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.saas.common.security.config.UserPro;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        Assert.notNull(sysUser,"用户不存在！");
        //查询用户权限信息
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("haha"));
        //把数据封装到UserDetails
        return new UserPro(sysUser,grantedAuthorities);
    }
}
