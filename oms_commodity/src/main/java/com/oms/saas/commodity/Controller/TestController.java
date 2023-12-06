package com.oms.saas.commodity.Controller;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.Store.SimulationStoreInfoDto;
import com.oms.saas.commodity.service.impl.Warehouse.WmsSimulationStoreInfoServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Resource
    private WmsSimulationStoreInfoServiceImpl wmsSimulationStoreInfoService;


    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/echo/{string}")
    public Result echo(@PathVariable String string) {
        return Result.success("Hello," + string);
    }

    @GetMapping("/testCache")
    public Result testCache(){
        SimulationStoreInfoDto simulationStoreInfo = wmsSimulationStoreInfoService.getSimulationStoreInfoDto("VC0001");
        return Result.success("Hello," + simulationStoreInfo.toString());

    }
}