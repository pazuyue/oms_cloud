package com.oms.saas.commodity.service.impl.Inventory;

import cn.hutool.core.util.ObjectUtil;
import com.oms.saas.commodity.Entity.Warehouse.WmsTickets;
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
import com.oms.saas.commodity.service.Warehouse.WmsTicketsGoodsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VirtuallyInventoryServiceInventoryServiceImpl implements InventoryService {

    @Resource
    private WmsTicketsMapper wmsTicketsMapper;
    @Resource
    private WmsTicketsGoodsService wmsTicketsGoodsService;
    @Resource
    private FeginInventoryService feginInventoryService;
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public boolean CGInventoryCallback(String sn) {
        this.wmsTicketsGoodsHandle(sn);
        WmsTicketsDto wmsTickets = wmsTicketsMapper.getOneWithDetali(sn);
        if (ObjectUtil.isEmpty(wmsTickets)){
            return false;
        }
        List<WmsTicketsGoods> wmsTicketsGoodsList = wmsTickets.getWmsTicketsGoodsList();
        for (WmsTicketsGoods wmsTicketsGoods : wmsTicketsGoodsList) {
            WmsInventoryBatch inventoryBatch = new WmsInventoryBatch();
            inventoryBatch.setWmsSimulationCode(wmsTickets.getWmsSimulationCode());
            inventoryBatch.setSkuSn(wmsTicketsGoods.getSkuSn());

            inventoryBatch.setZpActualNumber(wmsTicketsGoods.getNumberZp());
            inventoryBatch.setZpAvailableNumber(wmsTicketsGoods.getNumberZp());
            inventoryBatch.setCpActualNumber(wmsTicketsGoods.getNumberCp());
            inventoryBatch.setCpAvailableNumber(wmsTicketsGoods.getNumberCp());
            inventoryBatch.setBrandCode(wmsTicketsGoods.getBrandCode());
            inventoryBatch.setRemark(wmsTickets.getRemark());
            inventoryBatch.setBatchCode(wmsTicketsGoods.getBatchCode());
            inventoryBatch.setTransactionPrice(wmsTicketsGoods.getPurchasePrice());
            Result result = feginInventoryService.addInventory(jwtInfo.getToken(), inventoryBatch);
            if (result.getCode() == ResultCode.SUCCESS.getCode()){ //如果库存处理成功，更新明细
                WmsTicketsGoods updateWmsTticetGoods = new WmsTicketsGoods();
                updateWmsTticetGoods.setId(wmsTicketsGoods.getId());
                updateWmsTticetGoods.setInventoryIsHandle(DocumentState.PROCESSED_SUCCESS.getCode());
                wmsTicketsGoodsService.updateById(wmsTicketsGoods);
            }else {
                WmsTicketsGoods updateWmsTticetGoods = new WmsTicketsGoods();
                updateWmsTticetGoods.setId(wmsTicketsGoods.getId());
                updateWmsTticetGoods.setInventoryIsHandle(DocumentState.PROCESSED_FAIL.getCode());
                wmsTicketsGoodsService.updateById(wmsTicketsGoods);
            }
        }
        WmsTickets tickets = new WmsTickets();
        tickets.setStatusTicket(DocumentState.STATUS_TICKET_PROCESSED.getCode());
        tickets.setStatusQuery(DocumentState.STATUS_QUERY_PROCESSED_SUCCESS.getCode());
        tickets.setStatusNotify(DocumentState.STATUS_NOTIFY_PROCESSED_SUCCESS.getCode());
        tickets.setAcceptCallbackTime(new Date());
        wmsTicketsMapper.updateById(tickets);
        return false;
    }

    /**
     *
     * @param sn
     * @return
     */
    public Boolean wmsTicketsGoodsHandle(String sn)
    {
        WmsTicketsDto wmsTickets = wmsTicketsMapper.getOneWithDetali(sn);
        if (ObjectUtil.isEmpty(wmsTickets)){
            return false;
        }
        List<WmsTicketsGoods> wmsTicketsGoodsList = wmsTickets.getWmsTicketsGoodsList();
        for (WmsTicketsGoods wmsTicketsGoods : wmsTicketsGoodsList) {
            WmsTicketsGoods ticketsGoods = new WmsTicketsGoods();
            ticketsGoods.setId(wmsTicketsGoods.getId());
            ticketsGoods.setNumberActually(wmsTicketsGoods.getNumberExpected());
            if (wmsTicketsGoods.getInventoryType() == DocumentState.ZP.getMsg()){
                ticketsGoods.setNumberZp(wmsTicketsGoods.getNumberExpected());
            }else {
                ticketsGoods.setNumberCp(wmsTicketsGoods.getNumberExpected());
            }
            wmsTicketsGoodsService.updateById(ticketsGoods);
        }
        return true;
    }
}
