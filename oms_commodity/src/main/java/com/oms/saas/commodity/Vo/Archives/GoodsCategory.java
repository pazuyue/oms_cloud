package com.oms.saas.commodity.Vo.Archives;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GoodsCategory implements Serializable {
    private Integer id;
    /**
     * 关联品牌
     */
    @NotBlank(message = "关联品牌不能为空")
    private String brandCode;
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * 1级别，2级别，3级别
     */
    private Byte level;
}
