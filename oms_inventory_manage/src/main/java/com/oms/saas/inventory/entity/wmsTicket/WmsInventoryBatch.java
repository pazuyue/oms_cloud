package com.oms.saas.inventory.entity.wmsTicket;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 仓库库存表-批次维度：sku_sn + company_code + batch_code + store_code
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@TableName("wms_inventory_batch")
public class WmsInventoryBatch implements Serializable {

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
     * 正品可用库存
     */
    private Integer zpAvailableNumber;

    /**
     * 次品可用库存
     */
    private Integer cpAvailableNumber;

    /**
     * 正品预占库存(总)
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
     * 采购批次
     */
    private String batchCode;

    /**
     * 批次的成本价
     */
    private BigDecimal transactionPrice;

    /**
     * 品牌编码
     */
    private String brandCode;
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

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
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
        return "WmsInventoryBatch{" +
        ", id = " + id +
        ", storeCode = " + storeCode +
        ", skuSn = " + skuSn +
        ", zpActualNumber = " + zpActualNumber +
        ", cpActualNumber = " + cpActualNumber +
        ", zpAvailableNumber = " + zpAvailableNumber +
        ", cpAvailableNumber = " + cpAvailableNumber +
        ", zpLockNumber = " + zpLockNumber +
        ", cpLockNumber = " + cpLockNumber +
        ", remark = " + remark +
        ", batchCode = " + batchCode +
        ", transactionPrice = " + transactionPrice +
        ", brandCode = " + brandCode +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        "}";
    }
}
