package com.oms.saas.commodity.service.impl.Inventory;

import cn.hutool.core.util.ObjectUtil;
import com.oms.saas.commodity.Entity.Warehouse.WmsTickets;
import com.oms.saas.commodity.dto.Warehouse.WmsTicketsDto;
import com.oms.saas.commodity.mapper.Warehouse.WmsTicketsMapper;
import com.oms.saas.commodity.service.Inventory.InventoryService;
import com.oms.saas.commodity.service.Warehouse.WmsTicketsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private WmsTicketsMapper wmsTicketsMapper;
    @Override
    public boolean CGInventoryCallback(String sn) {
        WmsTicketsDto wmsTickets = wmsTicketsMapper.getOneWithDetali(sn);
        if (ObjectUtil.isEmpty(wmsTickets)){
            return false;
        }
        System.out.println(wmsTickets.toString());
        return false;
    }
}
