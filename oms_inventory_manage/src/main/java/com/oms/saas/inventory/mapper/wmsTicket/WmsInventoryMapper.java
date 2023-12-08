package com.oms.saas.inventory.mapper.wmsTicket;

import com.oms.saas.inventory.entity.wmsTicket.WmsInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oms.saas.inventory.entity.wmsTicket.WmsInventoryBatch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 仓库库存表维度：sku_sn + store_code Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
public interface WmsInventoryMapper extends BaseMapper<WmsInventory> {
    @Insert("<script>" +
            " INSERT INTO wms_inventory(store_code,sku_sn,zp_actual_number,cp_actual_number,zp_available_number,cp_available_number,remark,brand_code) VALUES " +
            " (#{wms.storeCode},#{wms.skuSn},#{wms.zpActualNumber},#{wms.cpActualNumber},#{wms.zpAvailableNumber},#{wms.cpAvailableNumber},#{wms.remark},#{wms.brandCode})" +
            " on duplicate key update zp_actual_number = zp_actual_number+#{wms.zpActualNumber},zp_available_number = zp_available_number+#{wms.zpAvailableNumber}," +
            " cp_actual_number = cp_actual_number+#{wms.cpActualNumber},cp_available_number = cp_available_number+#{wms.cpAvailableNumber}" +
            " </script>")
    int insertOrUpdate(@Param("wms") WmsInventory wms);
}
