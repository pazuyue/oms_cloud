package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Archives.GoodsCategory;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.geberator.GoodsCategoryMapper;
import com.oms.saas.commodity.service.Goods.GoodsCategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Resource
    private JwtInfo jwtInfo;
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public int save(GoodsCategory goodsCategory) {
        System.out.println("save"+goodsCategory);
        Object p = goodsCategory.getPid();
        if (!ObjectUtil.isEmpty(p)){
            int pid = (int) p;
            GoodsCategory goodsCategory1 = goodsCategoryMapper.selectById(pid);
            System.out.println("goodsCategory1"+goodsCategory1);
            if (ObjectUtil.isEmpty(goodsCategory1)){
                throw new RuntimeException("父ID不存在");
            }
            int level = goodsCategory1.getLevel() + 1;
            goodsCategory.setLevel((byte) level);
        }
        goodsCategory.setCompanyCode(jwtInfo.getCompanyCode());
        return goodsCategoryMapper.insert(goodsCategory);
    }

    @Override
    public int deleteById(int id) {
        return goodsCategoryMapper.deleteById(id);
    }

    public Integer selectCategoryCode(String categoryName)
    {
        QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",categoryName).eq("level",3);
        GoodsCategory goodsCategory = goodsCategoryMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(goodsCategory)){
            return 0;
        }
        return goodsCategoryMapper.selectOne(queryWrapper).getId();
    }
}
