package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.entity.Goods.GoodsSkuSnInfo;
import com.oms.saas.commodity.mapper.Goods.GoodsSkuSnInfoMapper;
import com.oms.saas.commodity.service.Goods.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品信息表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-07-17
 */
@Service
public class GoodsSkuSnInfoServiceImpl extends ServiceImpl<GoodsSkuSnInfoMapper, GoodsSkuSnInfo> implements GoodsSkuSnInfoService {


    @Resource
    GoodsColorService goodsColorService;
    @Resource
    GoodsSizeService goodsSizeService;
    @Resource
    GoodsCategoryService goodsCategoryService;
    @Resource
    private GoodsSkuSnInfoTmpService goodsSkuSnInfoTmpService;
    @Override
    public boolean toExamine(String importBatch) {
        QueryWrapper<GoodsSkuSnInfoTmp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notes","正常");
        GoodsSkuSnInfoTmp infoTmp = goodsSkuSnInfoTmpService.getOne(queryWrapper);
        if (!ObjectUtil.isEmpty(infoTmp)){
            throw new RuntimeException("审核失败，请先处理异常导入信息");
        }
        List<GoodsSkuSnInfoTmp> list = goodsSkuSnInfoTmpService.list(queryWrapper);
        List<GoodsSkuSnInfo> goodsSkuSnInfoArrayList = new ArrayList<>();
        for(GoodsSkuSnInfoTmp tmp:list) {
            Integer colorCode = goodsColorService.selectOrSaveByColorName(tmp.getColorCode());
            if (ObjectUtil.isEmpty(colorCode))
                throw new RuntimeException("审核失败，色号处理异常");
            Integer sizeCode = goodsSizeService.selectOrSaveBySizeName(tmp.getSizeCode());
            if (ObjectUtil.isEmpty(sizeCode))
                throw new RuntimeException("审核失败，尺码处理异常");
            Integer categoryCode = goodsCategoryService.selectCategoryCode(tmp.getGoodsName());
            if (ObjectUtil.isEmpty(categoryCode))
                throw new RuntimeException("审核失败，类名不存在");
            GoodsSkuSnInfo goods = new GoodsSkuSnInfo();
            goods.setSkuSn(tmp.getSkuSn());
            goods.setGoodsSn(tmp.getGoodsSn());
            goods.setBarcodeSn(tmp.getBarcodeSn());
            goods.setGoodsName(tmp.getGoodsName());
            goods.setCategoryCode(categoryCode);
            goods.setColorCode(colorCode);
            goods.setSizeCode(sizeCode);
            goods.setMarketPrice(tmp.getMarketPrice());
            goods.setValidity(tmp.getValidity());
            goods.setGoodsDesc(tmp.getGoodsDesc());
            goods.setIsFd(tmp.getIsFd());
            goods.setIsGift(tmp.getIsGift());
            goods.setDescription(tmp.getNotes());
            goodsSkuSnInfoArrayList.add(goods);
        }

        return this.saveBatch(goodsSkuSnInfoArrayList);
    }
}
