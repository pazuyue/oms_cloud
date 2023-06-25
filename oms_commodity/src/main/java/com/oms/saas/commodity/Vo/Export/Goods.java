package com.oms.saas.commodity.Vo.Export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Goods {
    @Excel(name = "SKU(系统唯一，≤30位，可英文数字符号（除空格外），必填)", width = 20)
    private String skuSn;
    @Excel(name = "条形码(系统唯一，可支持英文数字、“-”、“_”，≤30位数字，必填)", width = 20)
    private String barcodeSn;
    @Excel(name = "货号(系统唯一，≤30位，可英文数字符号（除空格外），必填)", width = 20)
    private String goodsSn;
    @Excel(name = "商品名称(≤100字符，不限格式，必填)", width = 20)
    private String goodsName;
    @Excel(name = "分类(第三级分类名称，必填)", width = 20)
    private String categoryName;
    @Excel(name = "颜色(≤30字符，不限格式，必填)", width = 20)
    private String colorName;
    @Excel(name = "尺码(≤30字符，不限格式，必填)", width = 20)
    private String sizeName;
    @Excel(name = "市场价(吊牌价，≥0，必填)", width = 20)
    private BigDecimal marketPrice;
    @Excel(name = "有效期(填写整数，需≥0，单位：天)", width = 20)
    private Integer validity;
    @Excel(name = "商品描述(非必填)", width = 20)
    private String description;
}
