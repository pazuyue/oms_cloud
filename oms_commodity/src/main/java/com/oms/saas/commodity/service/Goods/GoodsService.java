package com.oms.saas.commodity.service.Goods;

import com.oms.saas.commodity.Vo.Export.GoodsVO;

import java.util.List;

public interface GoodsService {
    public boolean export(List<GoodsVO> list);
}
