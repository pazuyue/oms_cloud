package com.oms.saas.commodity.Controller;

import com.oms.saas.commodity.Entity.wmsTicket.WmsInventoryBatch;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.dto.Store.SimulationStoreInfoDto;
import com.oms.saas.commodity.service.FeignClients.Inventory.FeginInventoryService;
import com.oms.saas.commodity.service.impl.Warehouse.WmsSimulationStoreInfoServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Resource
    private WmsSimulationStoreInfoServiceImpl wmsSimulationStoreInfoService;
    @Resource
    private FeginInventoryService feginInventoryService;
    @Resource
    private JwtInfo jwtInfo;

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

    @GetMapping("/testAddInventory")
    public Result testAddInventory()
    {
        WmsInventoryBatch inventoryBatch = new WmsInventoryBatch();
        inventoryBatch.setWmsSimulationCode("V0001");
        inventoryBatch.setSkuSn("test01");
        inventoryBatch.setZpActualNumber(100);
        inventoryBatch.setZpAvailableNumber(100);
        inventoryBatch.setBrandCode("QM");
        inventoryBatch.setRemark("测试库存添加");
        inventoryBatch.setBatchCode("001");
        inventoryBatch.setTransactionPrice(new BigDecimal("0.00"));
        return feginInventoryService.addInventory(jwtInfo.getToken(),inventoryBatch);
    }

    @GetMapping("/testGetOne")
    public Result testGetOne()
    {
        return feginInventoryService.getByOne(jwtInfo.getToken(),"test01");
    }
}