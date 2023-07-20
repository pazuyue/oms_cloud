package com.saas.common.security.mapper.User;

import com.saas.common.security.dto.UserDTO;
import com.saas.common.security.entity.User.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-06-27
 */

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where id =#{id}")
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "brandInfoList", column = "id", javaType = List.class,
                    many = @Many(select = "com.saas.common.security.mapper.Brand.BrandInfoMapper.selectBranInfoByUserId"))
    })
    UserDTO selectUserInfoWtihBrand(int id);
}
