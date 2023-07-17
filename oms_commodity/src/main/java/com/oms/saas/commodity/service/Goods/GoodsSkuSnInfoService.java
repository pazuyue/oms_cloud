package com.oms.saas.commodity.service.Goods;

import com.oms.saas.commodity.entity.Goods.GoodsSkuSnInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品信息表 服务类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-17
 */
public interface GoodsSkuSnInfoService extends IService<GoodsSkuSnInfo> {

    /**
     * 审核
     * @param importBatch
     * @return
     */
    public boolean toExamine(String importBatch);
}
