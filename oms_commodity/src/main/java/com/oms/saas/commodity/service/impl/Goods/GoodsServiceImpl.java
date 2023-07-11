package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.mapper.Goods.GoodsSkuSnInfoTmpMapper;
import com.oms.saas.commodity.service.Goods.GoodsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsSkuSnInfoTmpMapper,GoodsSkuSnInfoTmp> implements GoodsService {

    public boolean export(List<GoodsVO> list) {
        List<GoodsSkuSnInfoTmp> goodsSkuSnInfoTmpList = new ArrayList<>();
        list.forEach(vo->{
            GoodsSkuSnInfoTmp goodsSkuSnInfoTmp = new GoodsSkuSnInfoTmp();
            goodsSkuSnInfoTmp.setSkuSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setGoodsSn(vo.getGoodsSn());
            goodsSkuSnInfoTmp.setBarcodeSn(vo.getBarcodeSn());
            goodsSkuSnInfoTmp.setGoodsName(vo.getGoodsName());
            goodsSkuSnInfoTmp.setCategoryCode(vo.getCategoryName());
            goodsSkuSnInfoTmp.setColorCode(vo.getColorName());
            goodsSkuSnInfoTmp.setSizeCode(vo.getSizeName());
            goodsSkuSnInfoTmp.setMarketPrice(vo.getMarketPrice());
            goodsSkuSnInfoTmp.setGoodsDesc(vo.getDescription());
            goodsSkuSnInfoTmpList.add(goodsSkuSnInfoTmp);
        });
        Console.log(goodsSkuSnInfoTmpList);
        return this.saveBatch(goodsSkuSnInfoTmpList);
    }
}
