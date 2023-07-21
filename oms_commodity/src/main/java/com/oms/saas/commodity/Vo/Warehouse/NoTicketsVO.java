package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class NoTicketsVO {
    /**
     * 入库单名称
     */
    @NotBlank(message = "入库单名称不能为空")
    private String noName;
    /**
     * 计划到货时间
     */
    private Date expectedCallbackTime;
    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remarks;
}
