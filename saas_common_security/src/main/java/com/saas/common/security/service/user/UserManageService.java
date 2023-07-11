package com.saas.common.security.service.user;

import com.saas.common.security.entity.User.SysUser;
import org.springframework.stereotype.Service;


public interface UserManageService {

    public SysUser getUserInfoByToken(String token);

    public int register(SysUser sysUser);
}
