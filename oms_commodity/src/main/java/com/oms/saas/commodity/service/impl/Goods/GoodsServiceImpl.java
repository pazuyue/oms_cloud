package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.lang.Console;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.mapper.Goods.GoodsSkuSnInfoTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsSkuSnInfoTmpMapper goodsSkuSnInfoTmpMapper;
    @Override
    public boolean export(List<GoodsVO> list) {
        List<GoodsSkuSnInfoTmp> goodsSkuSnInfoTmpList = new ArrayList<>();
        list.forEach(vo->{
            GoodsSkuSnInfoTmp goodsSkuSnInfoTmp = new GoodsSkuSnInfoTmp();
            goodsSkuSnInfoTmp.setGoodsSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setSkuSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setBarcodeSn(vo.getBarcodeSn());
            goodsSkuSnInfoTmp.setGoodsName(vo.getGoodsName());
            goodsSkuSnInfoTmp.setMarketPrice(vo.getMarketPrice());
            goodsSkuSnInfoTmp.setGoodsDesc(vo.getDescription());
            goodsSkuSnInfoTmpList.add(goodsSkuSnInfoTmp);
        });
        Console.log(goodsSkuSnInfoTmpList);
        //goodsSkuSnInfoTmpMapper.insertBatchSomeColumn(list);
        return false;
    }
}
