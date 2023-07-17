package com.oms.saas.commodity.service.impl.Goods;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oms.saas.commodity.Entity.Archives.GoodsCategory;
import com.oms.saas.commodity.mapper.geberator.GoodsCategoryMapper;
import com.oms.saas.commodity.service.Goods.GoodsCategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public int save(GoodsCategory goodsCategory) {
        System.out.println("save"+goodsCategory);
        int pid = goodsCategory.getPid();
        if (!ObjectUtil.isEmpty(pid)){
            GoodsCategory goodsCategory1 = goodsCategoryMapper.selectById(pid);
            System.out.println("goodsCategory1"+goodsCategory1);
            if (ObjectUtil.isEmpty(goodsCategory1)){
                throw new RuntimeException("父ID不存在");
            }
            int level = goodsCategory1.getLevel() + 1;
            goodsCategory.setLevel((byte) level);
        }

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
        return goodsCategoryMapper.selectOne(queryWrapper).getId();
    }
}
