package com.oms.saas.commodity.Entity.Goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品信息临时表
 * </p>
 *
 * @author 月光光
 * @since 2023-07-11
 */
@TableName("goods_sku_sn_info_tmp")
public class GoodsSkuSnInfoTmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku
     */
    private String skuSn;

    /**
     * 货号
     */
    private String goodsSn;

    /**
     * 条形码
     */
    private String barcodeSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 类目code
     */
    private String categoryCode;

    /**
     * 颜色编码
     */
    private String colorCode;

    /**
     * 尺码
     */
    private String sizeCode;

    /**
     * 市场价（吊牌价）
     */
    private String marketPrice;

    /**
     * 需要有效期管理
     */
    private String isNeedEffect;

    /**
     * 有效期
     */
    private String validity;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 是否福袋,
     */
    private String isFd;

    /**
     * 是否赠品
     */
    private String isGift;
    private Date deletedAt;

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 公司编码
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuSn() {
        return skuSn;
    }

    public void setSkuSn(String skuSn) {
        this.skuSn = skuSn;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getBarcodeSn() {
        return barcodeSn;
    }

    public void setBarcodeSn(String barcodeSn) {
        this.barcodeSn = barcodeSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getIsNeedEffect() {
        return isNeedEffect;
    }

    public void setIsNeedEffect(String isNeedEffect) {
        this.isNeedEffect = isNeedEffect;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getIsFd() {
        return isFd;
    }

    public void setIsFd(String isFd) {
        this.isFd = isFd;
    }

    public String getIsGift() {
        return isGift;
    }

    public void setIsGift(String isGift) {
        this.isGift = isGift;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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
        return "GoodsSkuSnInfoTmp{" +
        ", id = " + id +
        ", skuSn = " + skuSn +
        ", goodsSn = " + goodsSn +
        ", barcodeSn = " + barcodeSn +
        ", goodsName = " + goodsName +
        ", brandCode = " + brandCode +
        ", brandName = " + brandName +
        ", categoryCode = " + categoryCode +
        ", colorCode = " + colorCode +
        ", sizeCode = " + sizeCode +
        ", marketPrice = " + marketPrice +
        ", isNeedEffect = " + isNeedEffect +
        ", validity = " + validity +
        ", goodsDesc = " + goodsDesc +
        ", isFd = " + isFd +
        ", isGift = " + isGift +
        ", deletedAt = " + deletedAt +
        ", batchId = " + batchId +
        ", companyCode = " + companyCode +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        "}";
    }
}
