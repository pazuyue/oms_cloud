package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Goods.GoodsColor;
import com.oms.saas.commodity.Entity.Goods.GoodsSize;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Goods.GoodsSizeMapper;
import com.oms.saas.commodity.service.Goods.GoodsSizeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsSizeServiceImpl implements GoodsSizeService {

    @Resource
    private GoodsSizeMapper goodsSizeMapper;
    @Resource
    private JwtInfo jwtInfo;
    @Override
    public Integer selectOrSaveBySizeName(String size_name) {
        QueryWrapper<GoodsSize> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("size_name",size_name);
        GoodsSize goodsSize = goodsSizeMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(goodsSize)){
            GoodsSize size = new GoodsSize();
            size.setSizeName(size_name);
            size.setCompanyCode(jwtInfo.getCompanyCode());
            return goodsSizeMapper.insert(size);
        }
        return goodsSize.getId();
    }
}
