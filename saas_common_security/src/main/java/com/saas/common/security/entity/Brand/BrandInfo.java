package com.saas.common.security.entity.Brand;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 品牌信息表
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
@TableName("brand_info")
@Data
public class BrandInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;
    private Date createTime;
    private Date modifyTime;
}
