package com.oms.saas.commodity.mapper.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsSimulationStoreInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oms.saas.commodity.dto.SimulationStoreInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 虚仓表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-08-02
 */
public interface WmsSimulationStoreInfoMapper extends BaseMapper<WmsSimulationStoreInfo> {

    @Select("select * from wms_simulation_store_info where id=#{id}")
    @Results({
            @Result(property = "ownerCode", column = "owner_code"),
            @Result(property = "ownerInfo", column = "owner_code", javaType = OwnerInfo.class,
                    one = @One(select = "com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper.selectOwnerInfoByOwnerCode"))
    })
    SimulationStoreInfoDto selectSimulationStoreInfoWtihOwnerInfo(int id);
}
