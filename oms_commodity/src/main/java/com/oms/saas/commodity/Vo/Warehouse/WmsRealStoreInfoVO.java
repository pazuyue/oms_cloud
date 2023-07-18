package com.oms.saas.commodity.Vo.Warehouse;

import lombok.Data;

@Data
public class WmsRealStoreInfoVO {
    /**
     * 1:电商仓，2：门店仓，3：零售仓
     */
    private Byte wmsType;
    /**
     * 实仓编码
     */
    private String realStoreCode;

    /**
     * 实仓名称
     */
    private String wmsName;

    /**
     * 仓库负责人
     */
    private String director;

    /**
     * 联系电话
     */
    private String mobilePhone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 公司编码
     */
    private String companyCode;
    /**
     * 货主
     */
    private String ownerCode;
}
