package com.oms.saas.commodity.service.Goods;

import com.oms.saas.commodity.Entity.Archives.GoodsCategory;

public interface GoodsCategoryService {
    /**
     * 保存
     * @param goodsCategory
     * @return
     */
    public int save(GoodsCategory goodsCategory);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(int id);
}
