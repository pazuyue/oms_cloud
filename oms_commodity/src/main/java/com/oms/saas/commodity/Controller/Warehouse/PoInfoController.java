package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.lang.Console;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.PoInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.PoInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poInfo")
public class PoInfoController {

    @Resource
    private PoInfoService poInfoService;

    @SneakyThrows
    @PostMapping(value = "/save")
    public Result save(@Validated PoInfoVO poInfoVO){
        Console.log(poInfoVO.toString());
        if (poInfoService.save(poInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }
}
