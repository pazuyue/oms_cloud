package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Goods.GoodsColor;
import com.oms.saas.commodity.mapper.Goods.GoodsColorMapper;
import com.oms.saas.commodity.service.Goods.GoodsColorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsColorServiceImpl implements GoodsColorService {

    @Resource
    private GoodsColorMapper goodsColorMapper;
    @Override
    public Integer selectOrSaveByColorName(String color_name) {
        QueryWrapper<GoodsColor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("color_name",color_name);
        GoodsColor goodsColor = goodsColorMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(goodsColor)){
            GoodsColor color = new GoodsColor();
            color.setColorName("color_name");
            return goodsColorMapper.insert(color);
        }

        return goodsColor.getId();
    }
}
