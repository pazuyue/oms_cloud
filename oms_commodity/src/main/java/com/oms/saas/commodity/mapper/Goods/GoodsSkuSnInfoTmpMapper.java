package com.oms.saas.commodity.mapper.Goods;

import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品信息临时表 Mapper 接口
 * </p>
 *
 * @author 月光光
 * @since 2023-07-11
 */
public interface GoodsSkuSnInfoTmpMapper extends BaseMapper<GoodsSkuSnInfoTmp> {
    /**
     * 批量插入 仅适用于mysql
     * @param goodsSkuSnInfoTmp 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<GoodsSkuSnInfoTmp> goodsSkuSnInfoTmp);
}
