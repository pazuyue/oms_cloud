package com.oms.saas.commodity.mapper.Warehouse;

import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsRealStoreInfo;
import com.oms.saas.commodity.Entity.Warehouse.WmsTickets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oms.saas.commodity.dto.Warehouse.WmsTicketsDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 出入库单 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-08-03
 */
public interface WmsTicketsMapper extends BaseMapper<WmsTickets> {

    @Select("select * from wms_tickets WHERE sn =#{sn}")
    @Results({
            @Result(property = "sn", column = "sn"),
            @Result(property = "wmsTicketsGoodsList", column = "sn", javaType = List.class,
                    many = @Many(select = "com.oms.saas.commodity.mapper.Warehouse.WmsTicketsGoodsMapper.selectBySn"))
    })
    WmsTicketsDto getOneWithDetali(String sn);
}
