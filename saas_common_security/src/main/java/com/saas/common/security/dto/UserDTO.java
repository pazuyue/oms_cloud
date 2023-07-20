package com.saas.common.security.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.saas.common.security.entity.User.SysUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
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
    private String companyCode;

    /**
     * 1 普通会员 2管理员
     */
    private Byte userType;
    private Date createTime;
    private Date modifyTime;
    private List<BrandInfo> brandInfoList;
}
