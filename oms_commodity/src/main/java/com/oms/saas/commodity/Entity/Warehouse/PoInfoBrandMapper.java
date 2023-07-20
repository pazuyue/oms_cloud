package com.oms.saas.commodity.Entity.Warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 采购入库po单的品牌编码：采购入库po单+品牌编码对应表
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
@TableName("po_info_brand_mapper")
@Data
public class PoInfoBrandMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * po_info表的po_sn
     */
    private String poSn;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
