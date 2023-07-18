package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.lang.Console;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.WmsRealStoreInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.OwnerInfoService;
import com.oms.saas.commodity.service.Warehouse.WmsRealStoreInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realStore")
public class WmsRealStoreInfoController {
    @Resource
    private WmsRealStoreInfoService wmsRealStoreInfoService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @SneakyThrows
    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@Validated WmsRealStoreInfoVO wmsRealStoreInfoVO){
        if (wmsRealStoreInfoService.save(wmsRealStoreInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }
}
