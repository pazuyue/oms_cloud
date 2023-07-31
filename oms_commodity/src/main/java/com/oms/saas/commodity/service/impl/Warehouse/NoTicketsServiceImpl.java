package com.oms.saas.commodity.service.impl.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.oms.saas.commodity.Entity.Warehouse.NoTickets;
import com.oms.saas.commodity.Vo.Warehouse.NoTicketsVO;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.NoTicketsMapper;
import com.oms.saas.commodity.service.Warehouse.NoTicketsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
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

    @Resource
    private JwtInfo jwtInfo;
    @Override
    public boolean save(NoTicketsVO vo) {
        NoTickets noTickets = new NoTickets();
        BeanUtil.copyProperties(vo,noTickets);
        String batchCode =  IdUtil.simpleUUID();
        String noSn = "NO"+batchCode;
        noTickets.setNoSn(noSn);
        noTickets.setBatchCode(batchCode);
        noTickets.setCompanyCode(jwtInfo.getCompanyCode());
        noTickets.setCreatedUser(jwtInfo.getNickName());
        return  this.save(noTickets);
    }
}
