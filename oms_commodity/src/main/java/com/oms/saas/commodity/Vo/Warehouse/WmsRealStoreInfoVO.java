package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WmsRealStoreInfoVO {
    /**
     * 1:电商仓，2：门店仓，3：零售仓
     */
    @NotEmpty(message = "实仓类型不能为空")
    private Byte wmsType;
    /**
     * 实仓编码
     */
    @NotBlank(message = "实仓编码不能为空")
    private String realStoreCode;

    /**
     * 实仓名称
     */
    @NotBlank(message = "实仓名称不能为空")
    private String wmsName;

    /**
     * 仓库负责人
     */
    @NotBlank(message = "仓库负责人不能为空")
    private String director;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String mobilePhone;

    /**
     * 省
     */
    @NotBlank(message = "省不能为空")
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "市不能为空")
    private String city;

    /**
     * 区
     */
    @NotBlank(message = "区不能为空")
    private String district;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 公司编码
     */
    private String companyCode;
    /**
     * 货主
     */
    @NotBlank(message = "货主不能为空")
    private String ownerCode;
}
