package com.oms.saas.commodity.dto.Store;

import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import lombok.Data;

import java.util.Date;

@Data
public class OwnerInfoDto {

    private Integer id;
    private String ownerCode;
    private String ownerName;
    private Byte isSync;
    private Byte isEnable;
    private String companyCode;
    private Date createTime;
    private Date modifyTime;
    private WmsRealStoreInfo realStoreInfo;
}
