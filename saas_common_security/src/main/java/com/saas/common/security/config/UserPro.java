package com.saas.common.security.config;

import com.saas.common.security.entity.User.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPro extends User {

    private SysUser sysUser;

    public UserPro(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUserName(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public void setMyUser(SysUser sysUser){
        this.sysUser = sysUser;
    }

    public SysUser getUser() {
        return this.sysUser;
    }

}
