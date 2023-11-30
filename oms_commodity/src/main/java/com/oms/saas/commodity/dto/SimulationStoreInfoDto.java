package com.oms.saas.commodity.dto;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import lombok.Data;

import java.util.Date;

@Data
public class SimulationStoreInfoDto {
    private Integer id;
    private Byte status;
    private String wmsSimulationCode;
    private String wmsSimulationName;
    private String ownerCode;
    private String ownerName;
    private String companyCode;
    private Date createTime;
    private Date modifyTime;
    private OwnerInfo ownerInfo;
}
