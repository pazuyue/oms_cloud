package com.oms.saas.commodity.service.FeignClients.Inventory;

import com.oms.saas.commodity.Entity.wmsTicket.WmsInventoryBatch;
import com.oms.saas.commodity.api.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("OmsInventoryManage")
public interface FeginInventoryService {

    @PostMapping(value = "wms-inventory/addInventory")
    public Result addInventory(@RequestHeader("token") String token,@Param("body") WmsInventoryBatch wmsInventoryBatch);
}
