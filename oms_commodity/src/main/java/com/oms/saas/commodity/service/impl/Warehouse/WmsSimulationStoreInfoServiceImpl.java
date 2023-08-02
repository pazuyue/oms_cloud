package com.oms.saas.commodity.service.impl.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.WmsSimulationStoreInfo;
import com.oms.saas.commodity.Vo.Warehouse.WmsSimulationStoreInfoVO;
import com.oms.saas.commodity.mapper.Warehouse.WmsSimulationStoreInfoMapper;
import com.oms.saas.commodity.service.Warehouse.WmsSimulationStoreInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 虚仓表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-02
 */
@Service
public class WmsSimulationStoreInfoServiceImpl extends ServiceImpl<WmsSimulationStoreInfoMapper, WmsSimulationStoreInfo> implements WmsSimulationStoreInfoService {

    @Override
    public boolean save(WmsSimulationStoreInfoVO vo) {
        return false;
    }
}
