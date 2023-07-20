package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.PoInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Warehouse.PoInfoVO;

/**
 * <p>
 * 采购单主表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
public interface PoInfoService extends IService<PoInfo> {

    public boolean save(PoInfoVO poInfoVO);

}
