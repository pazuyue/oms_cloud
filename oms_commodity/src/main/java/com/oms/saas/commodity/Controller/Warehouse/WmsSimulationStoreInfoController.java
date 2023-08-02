package com.oms.saas.commodity.Controller.Warehouse;

import com.oms.saas.commodity.Vo.Warehouse.WmsRealStoreInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.WmsSimulationStoreInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.WmsSimulationStoreInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 虚仓管理
 */
@RestController
@RequestMapping("/wmsSimulationStore")
public class WmsSimulationStoreInfoController {

    @Resource
    private WmsSimulationStoreInfoService wmsSimulationStoreInfoService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @SneakyThrows
    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@Validated WmsSimulationStoreInfoVO wmsRealStoreInfoVO){
        if (wmsSimulationStoreInfoService.save(wmsRealStoreInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }
}
