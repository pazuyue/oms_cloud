package com.oms.saas.commodity.Vo.Export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoTicketsGoodsTmpVO {
    @Excel(name = "SKU", width = 20)
    @NotBlank(message = "SKU不能为空")
    private String skuSn;
    @Excel(name = "采购价", width = 20)
    @Positive(message = "采购价格不能为空")
    private BigDecimal purchasePrice;
    @Excel(name = "数量", width = 20)
    @NotBlank(message = "数量不能为空")
    private int zpNumberExpected;
}
