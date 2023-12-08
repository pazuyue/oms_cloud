package com.oms.saas.inventory.service.impl.wmsTicket;

import com.oms.saas.inventory.entity.wmsTicket.TSkuInventory;
import com.oms.saas.inventory.mapper.wmsTicket.TSkuInventoryMapper;
import com.oms.saas.inventory.service.wmsTicket.TSkuInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存表维度：sku_sn + company_code 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@Service
public class TSkuInventoryServiceImpl extends ServiceImpl<TSkuInventoryMapper, TSkuInventory> implements TSkuInventoryService {

}
