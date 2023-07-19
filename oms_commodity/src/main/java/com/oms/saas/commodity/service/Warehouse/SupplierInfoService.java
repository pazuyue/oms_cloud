package com.oms.saas.commodity.service.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.SupplierInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Vo.Warehouse.SupplierInfoVO;

/**
 * <p>
 * 供应商主表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-19
 */
public interface SupplierInfoService extends IService<SupplierInfo> {

    public boolean save(SupplierInfoVO vo);

    public SupplierInfo findOneByOwnerInfoVO(SupplierInfoVO vo);

}
