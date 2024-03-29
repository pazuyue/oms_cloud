package com.saas.common.security.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.saas.common.security.entity.Brand.BrandInfo;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.entity.User.UserCompanys;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO extends SysUser{
    private String companyCode; //在线登录公司
    private List<BrandInfo> brandInfoList; //品牌集合
    private List<UserCompanys> userCompanysList; //公司集合
}
