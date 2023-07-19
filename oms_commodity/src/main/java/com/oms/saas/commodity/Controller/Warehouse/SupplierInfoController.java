package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.lang.Console;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.SupplierInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.SupplierInfoService;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplierInfo")
public class SupplierInfoController {

    SupplierInfoService supplierInfoService;

    @SneakyThrows
    @PostMapping(value = "/save")
    public Result save(@Validated SupplierInfoVO supplierInfoVO){
        if (supplierInfoService.save(supplierInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }
}
