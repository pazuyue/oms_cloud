package com.oms.saas.commodity.service.impl.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.NoTickets;
import com.oms.saas.commodity.mapper.Warehouse.NoTicketsMapper;
import com.oms.saas.commodity.service.Warehouse.NoTicketsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购入库通知单 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-21
 */
@Service
public class NoTicketsServiceImpl extends ServiceImpl<NoTicketsMapper, NoTickets> implements NoTicketsService {

    @Override
    public boolean save() {
        return false;
    }
}
