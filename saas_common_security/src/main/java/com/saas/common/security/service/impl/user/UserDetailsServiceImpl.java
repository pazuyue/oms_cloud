package com.saas.common.security.service.impl.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.saas.common.security.config.UserPro;
import com.saas.common.security.dto.UserDTO;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.entity.User.UserCompanys;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.mapper.User.UserCompanysMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private UserCompanysMapper userCompanysMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<SysUser> queryUser = new LambdaQueryWrapper<>();
        queryUser.eq(SysUser::getUserName,username);
        SysUser sysUser = sysUserMapper.selectOne(queryUser);
        Assert.notNull(sysUser,"用户不存在！");
        System.out.println("sysUserMapper"+sysUser.toString());
        LambdaQueryWrapper<UserCompanys> queryCompany = new LambdaQueryWrapper<>();
        queryCompany.eq(UserCompanys::getUserId,sysUser.getId());
        List<UserCompanys> userCompanys = userCompanysMapper.selectList(queryCompany);
        System.out.println("userCompanys"+userCompanys.toString());
        // 拷贝对象
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(sysUser, userDTO);
        userDTO.setUserCompanysList(userCompanys);
        //查询用户权限信息
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("haha"));
        //把数据封装到UserDetails
        return new UserPro(userDTO,grantedAuthorities);
    }
}
