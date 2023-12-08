package com.oms.saas.inventory.mapper.wmsTicket;

import com.oms.saas.inventory.entity.wmsTicket.TSkuInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oms.saas.inventory.entity.wmsTicket.WmsInventory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品库存表维度：sku_sn + company_code Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
public interface TSkuInventoryMapper extends BaseMapper<TSkuInventory> {
    @Insert("<script>" +
            " INSERT INTO t_sku_inventory(sku_sn,zp_actual_number,cp_actual_number,zp_available_number,cp_available_number,remark,brand_code) VALUES " +
            " (#{inventory.skuSn},#{inventory.zpActualNumber},#{inventory.cpActualNumber},#{inventory.zpAvailableNumber},#{inventory.cpAvailableNumber},#{inventory.remark},#{inventory.brandCode})" +
            " on duplicate key update zp_actual_number = zp_actual_number+#{inventory.zpActualNumber},zp_available_number = zp_available_number+#{inventory.zpAvailableNumber}," +
            " cp_actual_number = cp_actual_number+#{inventory.cpActualNumber},cp_available_number = cp_available_number+#{inventory.cpAvailableNumber}" +
            " </script>")
    int insertOrUpdate(@Param("inventory") TSkuInventory inventory);
}
