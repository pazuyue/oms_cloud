package com.saas.common.security.mapper.User;

import com.saas.common.security.entity.User.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-06-27
 */

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user limit 1")
    SysUser selectByAAAAId(int id);
}
