package com.oms.saas.commodity.Controller.Archives;

import cn.hutool.core.bean.BeanUtil;
import com.oms.saas.commodity.Entity.Archives.GoodsCategory;
import com.oms.saas.commodity.Vo.Archives.GoodsCategoryVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.GoodsCategoryService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archives")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    @PostMapping("/goods/save")
    public Result save(@Validated GoodsCategoryVO goodsCategoryVO) {
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtil.copyProperties(goodsCategoryVO,goodsCategory);
        int id = goodsCategoryService.save(goodsCategory);
        if (id>0){
            return Result.success();
        }
        return Result.failed("保存失败");
    }
}
