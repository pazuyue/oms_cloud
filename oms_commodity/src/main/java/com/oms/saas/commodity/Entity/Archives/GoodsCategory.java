package com.oms.saas.commodity.Entity.Archives;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品类目表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-25
 */
@TableName("goods_category")
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联品牌
     */
    private String brandCode;

    /**
     * 公司code
     */
    private String companyCode;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 1级别，2级别，3级别
     */
    private Byte level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
        ", id = " + id +
        ", brandCode = " + brandCode +
        ", companyCode = " + companyCode +
        ", name = " + name +
        ", pid = " + pid +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        ", level = " + level +
        "}";
    }
}
