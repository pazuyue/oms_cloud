package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.NoTickets;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Warehouse.NoTicketsVO;

/**
 * <p>
 * 采购入库通知单 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-21
 */
public interface NoTicketsService extends IService<NoTickets> {
     boolean save(NoTicketsVO vo);
     /**
      * 审核
      * @param noSn
      * @return
      */
     public boolean examine(String noSn);
}
