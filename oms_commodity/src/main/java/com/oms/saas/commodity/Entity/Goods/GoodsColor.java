package com.oms.saas.commodity.Entity.Goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品颜色表
 * </p>
 *
 * @author 月光光
 * @since 2023-07-11
 */
@TableName("goods_color")
public class GoodsColor implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 颜色
     */
    private String colorName;

    /**
     * 品牌code
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 外部颜色编码
     */
    private String outColorCode;

    /**
     * 公司code
     */
    private String companyCode;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOutColorCode() {
        return outColorCode;
    }

    public void setOutColorCode(String outColorCode) {
        this.outColorCode = outColorCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "GoodsColor{" +
        ", id = " + id +
        ", colorName = " + colorName +
        ", brandCode = " + brandCode +
        ", brandName = " + brandName +
        ", outColorCode = " + outColorCode +
        ", companyCode = " + companyCode +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        "}";
    }
}
