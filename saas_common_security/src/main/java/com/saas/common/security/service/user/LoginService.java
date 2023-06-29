package com.saas.common.security.service.user;

import com.saas.common.security.api.Result;
import com.saas.common.security.entity.User.SysUser;

public interface LoginService {

    public Result login(SysUser user);
}
