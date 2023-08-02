package com.oms.saas.commodity.Entity.Warehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 虚仓表
 * </p>
 *
 * @author 月光光
 * @since 2023-08-02
 */
@TableName("wms_simulation_store_info")
public class WmsSimulationStoreInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 1: 未开启，2:开启
     */
    private Byte status;

    /**
     * 虚仓编码
     */
    private String wmsSimulationCode;

    /**
     * 虚仓名称
     */
    private String wmsSimulationName;

    /**
     * 货主编码
     */
    private String ownerCode;

    /**
     * 货主名称
     */
    private String ownerName;

    /**
     * 公司编码
     */
    private String companyCode;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getWmsSimulationCode() {
        return wmsSimulationCode;
    }

    public void setWmsSimulationCode(String wmsSimulationCode) {
        this.wmsSimulationCode = wmsSimulationCode;
    }

    public String getWmsSimulationName() {
        return wmsSimulationName;
    }

    public void setWmsSimulationName(String wmsSimulationName) {
        this.wmsSimulationName = wmsSimulationName;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
        return "WmsSimulationStoreInfo{" +
        ", id = " + id +
        ", status = " + status +
        ", wmsSimulationCode = " + wmsSimulationCode +
        ", wmsSimulationName = " + wmsSimulationName +
        ", ownerCode = " + ownerCode +
        ", ownerName = " + ownerName +
        ", companyCode = " + companyCode +
        ", createTime = " + createTime +
        ", modifyTime = " + modifyTime +
        "}";
    }
}
