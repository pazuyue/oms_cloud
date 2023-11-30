package com.oms.saas.commodity.mapper.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 货主基础信息表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-07-18
 */
public interface OwnerInfoMapper extends BaseMapper<OwnerInfo> {

    @Select("select * from owner_info WHERE owner_code =#{owner_code}")
    OwnerInfo selectOwnerInfoByOwnerCode(String owner_code);
}
