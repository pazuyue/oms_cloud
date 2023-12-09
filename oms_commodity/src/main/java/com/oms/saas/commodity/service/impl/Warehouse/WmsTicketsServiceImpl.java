package com.oms.saas.commodity.service.impl.Warehouse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Warehouse.WmsTickets;
import com.oms.saas.commodity.mapper.Warehouse.WmsTicketsMapper;
import com.oms.saas.commodity.service.Warehouse.WmsTicketsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出入库单 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-03
 */
@Service
public class WmsTicketsServiceImpl extends ServiceImpl<WmsTicketsMapper, WmsTickets> implements WmsTicketsService {

    public WmsTickets getOne(String sn){
        QueryWrapper<WmsTickets> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn",sn);
        return this.getOne(queryWrapper);
    }
}
