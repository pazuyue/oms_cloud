package com.oms.saas.commodity.service.Goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;

import java.util.List;

public interface GoodsService extends IService<GoodsSkuSnInfoTmp> {
    public boolean export(List<GoodsVO> list);
}
