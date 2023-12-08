package com.oms.saas.inventory.service.impl.wmsTicket;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.inventory.entity.wmsTicket.WmsInventoryBatch;
import com.oms.saas.inventory.mapper.wmsTicket.WmsInventoryBatchMapper;
import com.oms.saas.inventory.service.wmsTicket.WmsInventoryBatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * <p>
 * 仓库库存表-批次维度：sku_sn + company_code + batch_code + store_code 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@Service
public class WmsInventoryBatchServiceImpl extends ServiceImpl<WmsInventoryBatchMapper, WmsInventoryBatch> implements WmsInventoryBatchService {

    public Boolean addInventory(WmsInventoryBatch wmsInventoryBatch)
    {
       try {
           this.getBaseMapper().insertOrUpdate(wmsInventoryBatch);
           return true;
       }catch (Throwable e){
           System.out.println(e.getCause().getMessage());
       }
       return false;
    }
}
