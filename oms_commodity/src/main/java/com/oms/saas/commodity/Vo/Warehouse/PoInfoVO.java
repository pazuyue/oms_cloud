package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PoInfoVO {
    /**
     * 采购单名称
     */
    @NotBlank(message = "采购单名称不能为空")
    private String poName;
    /**
     * 供应商编码
     */
    @NotBlank(message = "供应商编码不能为空")
    private String supplierSn;
    /**
     * 入库仓库编码
     */
    @NotBlank(message = "入库仓库编码不能为空")
    private String wmsSimulationCode;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 品牌信息
     */
    private List<String> brandCodes;
}
