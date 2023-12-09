package com.oms.saas.commodity.Entity.wmsTicket;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品库存表维度：sku_sn + company_code
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@TableName("t_sku_inventory")
public class TSkuInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku
     */
    private String skuSn;

    /**
     * 正品库存：实际库存发货时减少
     */
    private Integer zpActualNumber;

    /**
     * 次品库存：实际库存发货时减少
     */
    private Integer cpActualNumber;

    /**
     * 正品可用库存
     */
    private Integer zpAvailableNumber;

    /**
     * 次品可用库存
     */
    private Integer cpAvailableNumber;

    /**
     * 总预占库存：正品(退仓，B2B出库，调拨出库，其他出库等：所有单据的库存预占) ,包括套装的预占
     */
    private Integer zpLockNumber;

    /**
     * 正品预占库存
     */
    private Integer cpLockNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 修改时间
     */
    private Date modifyTime;
    private Date createTime;

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

    public Integer getZpActualNumber() {
        return zpActualNumber;
    }

    public void setZpActualNumber(Integer zpActualNumber) {
        this.zpActualNumber = zpActualNumber;
    }

    public Integer getCpActualNumber() {
        return cpActualNumber;
    }

    public void setCpActualNumber(Integer cpActualNumber) {
        this.cpActualNumber = cpActualNumber;
    }

    public Integer getZpAvailableNumber() {
        return zpAvailableNumber;
    }

    public void setZpAvailableNumber(Integer zpAvailableNumber) {
        this.zpAvailableNumber = zpAvailableNumber;
    }

    public Integer getCpAvailableNumber() {
        return cpAvailableNumber;
    }

    public void setCpAvailableNumber(Integer cpAvailableNumber) {
        this.cpAvailableNumber = cpAvailableNumber;
    }

    public Integer getZpLockNumber() {
        return zpLockNumber;
    }

    public void setZpLockNumber(Integer zpLockNumber) {
        this.zpLockNumber = zpLockNumber;
    }

    public Integer getCpLockNumber() {
        return cpLockNumber;
    }

    public void setCpLockNumber(Integer cpLockNumber) {
        this.cpLockNumber = cpLockNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TSkuInventory{" +
        ", id = " + id +
        ", skuSn = " + skuSn +
        ", zpActualNumber = " + zpActualNumber +
        ", cpActualNumber = " + cpActualNumber +
        ", zpAvailableNumber = " + zpAvailableNumber +
        ", cpAvailableNumber = " + cpAvailableNumber +
        ", zpLockNumber = " + zpLockNumber +
        ", cpLockNumber = " + cpLockNumber +
        ", remark = " + remark +
        ", brandCode = " + brandCode +
        ", modifyTime = " + modifyTime +
        ", createTime = " + createTime +
        "}";
    }
}
