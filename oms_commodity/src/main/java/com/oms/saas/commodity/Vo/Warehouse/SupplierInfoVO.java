package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class SupplierInfoVO {

    /**
     * 供应商简称
     */
    @NotBlank(message = "供应商简称不能为空")
    private String supplierName;

    /**
     * 公司全称
     */
    @NotBlank(message = "公司全称不能为空")
    private String companyName;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空")
    private String contactUser;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String contactTel;

    /**
     * 省
     */
    @NotBlank(message = "省信息不能为空")
    private String contactProvince;

    /**
     * 市
     */
    @NotBlank(message = "市信息不能为空")
    private String contactCity;

    /**
     * 区
     */
    @NotBlank(message = "区信息不能为空")
    private String contactArea;

    /**
     * 联系人地址
     */
    @NotBlank(message = "联系人地址不能为空")
    private String contactAddress;

    /**
     * 操作用户
     */
    private String perationUser;
}
