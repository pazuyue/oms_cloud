package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WmsSimulationStoreInfoVO {

    /**
     * 虚仓编码
     */
    @NotBlank(message = "虚仓编码不能为空")
    private String wmsSimulationCode;

    /**
     * 虚仓名称
     */
    @NotBlank(message = "虚仓名称不能为空")
    private String wmsSimulationName;

    /**
     * 货主编码
     */
    @NotBlank(message = "货主编码不能为空")
    private String ownerCode;

    /**
     * 货主名称
     */
    @NotBlank(message = "货主名称不能为空")
    private String ownerName;
}
