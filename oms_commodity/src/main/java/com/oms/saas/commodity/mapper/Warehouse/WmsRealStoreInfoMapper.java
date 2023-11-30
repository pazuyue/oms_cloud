package com.oms.saas.commodity.mapper.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 实仓表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-07-18
 */
public interface WmsRealStoreInfoMapper extends BaseMapper<WmsRealStoreInfo> {

    @Select("select * from wms_real_store_info WHERE owner_code =#{owner_code}")
    WmsRealStoreInfo selectRealStoreInfoByOwnerCode(String owner_code);
}
