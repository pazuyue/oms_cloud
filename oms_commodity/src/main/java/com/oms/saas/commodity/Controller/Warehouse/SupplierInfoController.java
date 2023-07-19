package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.SupplierInfo;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.SupplierInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.SupplierInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplierInfo")
public class SupplierInfoController {

    @Resource
    SupplierInfoService supplierInfoService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @SneakyThrows
    @PostMapping(value = "/save")
    public Result save(@Validated SupplierInfoVO supplierInfoVO){
        System.out.println(supplierInfoVO.toString());
        if (supplierInfoService.save(supplierInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }

    @SneakyThrows
    @PostMapping(value = "/list")
    public Result list(@RequestBody SupplierInfoVO supplierInfoVO, @RequestParam(value = "page",defaultValue = "1") Integer page){
        Page<SupplierInfo> list = supplierInfoService.list(supplierInfoVO, page, pageSize);
        return Result.success(list);
    }
}
