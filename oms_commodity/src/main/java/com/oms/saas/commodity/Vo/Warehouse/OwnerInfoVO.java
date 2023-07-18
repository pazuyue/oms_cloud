package com.oms.saas.commodity.Vo.Warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OwnerInfoVO {
    /**
     * 编主编码
     */
    @NotBlank(message = "编主编码不能为空")
    private String ownerCode;

    /**
     * 编主名称
     */
    @NotBlank(message = "编主名称不能为空")
    private String ownerName;
}
