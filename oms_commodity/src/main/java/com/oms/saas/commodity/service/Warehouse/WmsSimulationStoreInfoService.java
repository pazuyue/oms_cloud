package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.WmsSimulationStoreInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Warehouse.WmsSimulationStoreInfoVO;

/**
 * <p>
 * 虚仓表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-08-02
 */
public interface WmsSimulationStoreInfoService extends IService<WmsSimulationStoreInfo> {

    public boolean save(WmsSimulationStoreInfoVO vo);
}
