package com.oms.saas.inventory.entity.wmsTicket;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 仓库库存表维度：sku_sn + store_code
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@TableName("wms_inventory")
public class WmsInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 仓库编码
     */
    private String storeCode;

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
     * 可用库存-正品
     */
    private Integer zpAvailableNumber;

    /**
     * 可用库存-次品
     */
    private Integer cpAvailableNumber;

    /**
     * 电商预占库存-正品
     */
    private Integer zpChannelLockNumber;

    /**
     * 总正品预占库存,（退仓，B2B出库，其他出库，调拨出库,B2C订单），包装套装预占
     */
    private Integer zpLockNumber;

    /**
     * 次品预占库存
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

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public Integer getZpChannelLockNumber() {
        return zpChannelLockNumber;
    }

    public void setZpChannelLockNumber(Integer zpChannelLockNumber) {
        this.zpChannelLockNumber = zpChannelLockNumber;
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
        return "WmsInventory{" +
        ", id = " + id +
        ", storeCode = " + storeCode +
        ", skuSn = " + skuSn +
        ", zpActualNumber = " + zpActualNumber +
        ", cpActualNumber = " + cpActualNumber +
        ", zpAvailableNumber = " + zpAvailableNumber +
        ", cpAvailableNumber = " + cpAvailableNumber +
        ", zpChannelLockNumber = " + zpChannelLockNumber +
        ", zpLockNumber = " + zpLockNumber +
        ", cpLockNumber = " + cpLockNumber +
        ", remark = " + remark +
        ", brandCode = " + brandCode +
        ", modifyTime = " + modifyTime +
        ", createTime = " + createTime +
        "}";
    }
}
