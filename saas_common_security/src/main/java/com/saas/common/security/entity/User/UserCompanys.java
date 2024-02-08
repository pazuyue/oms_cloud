package com.saas.common.security.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@TableName("user_companys")
@Data
public class UserCompanys {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private Integer userId;

    /**
     * 公司编码
     */
    @NotBlank(message = "公司编码不能为空")
    private String companyCode;
}
