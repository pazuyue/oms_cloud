package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.Vo.Warehouse.WmsRealStoreInfoVO;

/**
 * <p>
 * 实仓表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-18
 */
public interface WmsRealStoreInfoService extends IService<WmsRealStoreInfo> {

    public boolean save(WmsRealStoreInfoVO vo);

    public WmsRealStoreInfo findOneByOwnerInfoVO(WmsRealStoreInfoVO vo);
}
