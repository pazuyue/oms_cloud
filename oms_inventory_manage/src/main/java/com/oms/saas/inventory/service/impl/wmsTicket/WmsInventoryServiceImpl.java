package com.oms.saas.inventory.service.impl.wmsTicket;

import com.oms.saas.inventory.entity.wmsTicket.WmsInventory;
import com.oms.saas.inventory.mapper.wmsTicket.WmsInventoryMapper;
import com.oms.saas.inventory.service.wmsTicket.WmsInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库库存表维度：sku_sn + store_code 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@Service
public class WmsInventoryServiceImpl extends ServiceImpl<WmsInventoryMapper, WmsInventory> implements WmsInventoryService {

}
