package com.saas.common.security.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-27
 */
@TableName("sys_user")
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 1 正常 2停用
     */
    private Byte status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 1 男 2女 0未知
     */
    private Byte sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 公司编码
     */
    @NotBlank(message = "公司编码不能为空")
    private String company_code;

    /**
     * 1 普通会员 2管理员
     */
    private Byte userType;
    private Date createTime;
    private Date modifyTime;
}
