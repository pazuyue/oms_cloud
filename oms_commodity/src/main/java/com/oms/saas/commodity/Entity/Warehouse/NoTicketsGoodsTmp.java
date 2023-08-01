package com.oms.saas.commodity.Entity.Warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 入库通知单明细-未送审
 * </p>
 *
 * @author 月光光
 * @since 2023-08-01
 */
@TableName("no_tickets_goods_tmp")
@Data
public class NoTicketsGoodsTmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 入库单号
     */
    private String noSn;

    /**
     * sku_sn
     */
    private String skuSn;

    /**
     * 批次
     */
    private String batchCode;

    /**
     * 条形码
     */
    private String barcodeSn;

    /**
     * 货号
     */
    private String goodsSn;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 计划入库-正品数量
     */
    private Integer zpNumberExpected;

    /**
     * 计划入库-次品
     */
    private Integer cpNumberExpected;

    /**
     * 实际入库-正品
     */
    private Integer zpNumberActually;

    /**
     * 实际入库-次品
     */
    private Integer cpNumberActually;

    /**
     * 入库差异-正品
     */
    private Integer zpDifNumberActually;

    /**
     * 实际入库-次品
     */
    private Integer cpDifNumberActually;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 错误信息
     */
    private String errorInfo;

    /**
     * 导入文件的文件名
     */
    private String fileName;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
