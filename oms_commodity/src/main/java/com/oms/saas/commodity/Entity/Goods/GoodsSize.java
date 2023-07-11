package com.oms.saas.commodity.entity.Goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品尺码表
 * </p>
 *
 * @author 月光光
 * @since 2023-07-11
 */
@TableName("goods_size")
public class GoodsSize implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 尺码名称
     */
    private String sizeName;

    /**
     * 关联品牌
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 公司code
     */
    private String companyCode;

    /**
     * 外部尺码编码
     */
    private String outSizeCode;

    /**
     * 创建时间
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

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOutSizeCode() {
        return outSizeCode;
    }

    public void setOutSizeCode(String outSizeCode) {
        this.outSizeCode = outSizeCode;
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
        return "GoodsSize{" +
        ", id = " + id +
        ", sizeName = " + sizeName +
        ", brandCode = " + brandCode +
        ", brandName = " + brandName +
        ", companyCode = " + companyCode +
        ", outSizeCode = " + outSizeCode +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        "}";
    }
}
