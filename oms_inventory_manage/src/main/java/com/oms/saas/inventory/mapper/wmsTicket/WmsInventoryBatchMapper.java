package com.oms.saas.inventory.mapper.wmsTicket;

import com.oms.saas.inventory.entity.wmsTicket.WmsInventoryBatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 仓库库存表-批次维度：sku_sn + company_code + batch_code + store_code Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
public interface WmsInventoryBatchMapper extends BaseMapper<WmsInventoryBatch> {

    @Insert("<script>" +
            " INSERT INTO wms_inventory_batch(wms_simulation_code,sku_sn,zp_actual_number,cp_actual_number,zp_available_number,cp_available_number,remark,batch_code,transaction_price,brand_code) VALUES " +
            " (#{wms.wmsSimulationCode},#{wms.skuSn},#{wms.zpActualNumber},#{wms.cpActualNumber},#{wms.zpAvailableNumber},#{wms.cpAvailableNumber},#{wms.remark},#{wms.batchCode},#{wms.transactionPrice},#{wms.brandCode})" +
            " on duplicate key update zp_actual_number = zp_actual_number+#{wms.zpActualNumber},zp_available_number = zp_available_number+#{wms.zpAvailableNumber}," +
            " cp_actual_number = cp_actual_number+#{wms.cpActualNumber},cp_available_number = cp_available_number+#{wms.cpAvailableNumber}" +
            " </script>")
    int insertOrUpdate(@Param("wms") WmsInventoryBatch wms);
}
