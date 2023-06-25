package com.oms.saas.commodity.Controller.Archives;

import com.oms.saas.commodity.Vo.Archives.GoodsCategory;
import com.oms.saas.commodity.api.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archives")
public class GoodsCategoryController {

    @PostMapping("/goods/save")
    public Result save(@RequestBody @Validated GoodsCategory goodsCategory) {
        return Result.success(goodsCategory);
    }
}
