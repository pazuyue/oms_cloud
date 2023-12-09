package com.oms.saas.commodity.service.impl.Inventory;

import cn.hutool.core.util.ObjectUtil;
import com.oms.saas.commodity.Entity.Warehouse.WmsTicketsGoods;
import com.oms.saas.commodity.Entity.wmsTicket.WmsInventoryBatch;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.dto.Warehouse.WmsTicketsDto;
import com.oms.saas.commodity.mapper.Warehouse.WmsTicketsMapper;
import com.oms.saas.commodity.service.FeignClients.Inventory.FeginInventoryService;
import com.oms.saas.commodity.service.Inventory.InventoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private WmsTicketsMapper wmsTicketsMapper;
    @Resource
    private FeginInventoryService feginInventoryService;
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public boolean CGInventoryCallback(String sn) {
        WmsTicketsDto wmsTickets = wmsTicketsMapper.getOneWithDetali(sn);
        if (ObjectUtil.isEmpty(wmsTickets)){
            return false;
        }
        List<WmsTicketsGoods> wmsTicketsGoodsList = wmsTickets.getWmsTicketsGoodsList();
        for (WmsTicketsGoods wmsTicketsGoods : wmsTicketsGoodsList) {
            WmsInventoryBatch inventoryBatch = new WmsInventoryBatch();
            inventoryBatch.setWmsSimulationCode(wmsTickets.getWmsSimulationCode());
            inventoryBatch.setSkuSn("test01");
            inventoryBatch.setZpActualNumber(100);
            inventoryBatch.setZpAvailableNumber(100);
            inventoryBatch.setBrandCode("QM");
            inventoryBatch.setRemark("测试库存添加");
            inventoryBatch.setBatchCode("001");
            inventoryBatch.setTransactionPrice(new BigDecimal("0.00"));
            Result result = feginInventoryService.addInventory(jwtInfo.getToken(), inventoryBatch);
        }
        System.out.println();
        return false;
    }
}
