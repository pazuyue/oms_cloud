package com.oms.saas.commodity.service.impl.Inventory;

import cn.hutool.core.util.ObjectUtil;
import com.oms.saas.commodity.Entity.Warehouse.WmsTicketsGoods;
import com.oms.saas.commodity.Entity.wmsTicket.WmsInventoryBatch;
import com.oms.saas.commodity.api.DocumentState;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.api.ResultCode;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.dto.Warehouse.WmsTicketsDto;
import com.oms.saas.commodity.mapper.Warehouse.WmsTicketsMapper;
import com.oms.saas.commodity.service.FeignClients.Inventory.FeginInventoryService;
import com.oms.saas.commodity.service.Inventory.InventoryService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.ResultType;
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
            inventoryBatch.setSkuSn(wmsTicketsGoods.getSkuSn());

            if (wmsTicketsGoods.getInventoryType() == DocumentState.ZP.getMsg()){
                inventoryBatch.setZpActualNumber(wmsTicketsGoods.getNumberExpected());
                inventoryBatch.setZpAvailableNumber(wmsTicketsGoods.getNumberExpected());
            }else {
                inventoryBatch.setCpActualNumber(wmsTicketsGoods.getNumberExpected());
                inventoryBatch.setCpAvailableNumber(wmsTicketsGoods.getNumberExpected());
            }
            inventoryBatch.setRemark(wmsTickets.getRemark());
            inventoryBatch.setBatchCode(wmsTicketsGoods.getBatchCode());
            inventoryBatch.setTransactionPrice(wmsTicketsGoods.getPurchasePrice());
            Result result = feginInventoryService.addInventory(jwtInfo.getToken(), inventoryBatch);
            if (result.getCode() == ResultCode.SUCCESS.getCode()){ //如果库存处理成功，更新明细

            }
        }
        System.out.println();
        return false;
    }
}
