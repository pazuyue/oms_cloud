package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import com.oms.saas.commodity.Entity.Archives.GoodsCategory;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Vo.Archives.GoodsCategoryVO;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerInfoController {

    private OwnerInfoMapper ownerInfoMapper;

    public Result save(@Validated OwnerInfoVO ownerInfoVO){
        OwnerInfo ownerInfo = new OwnerInfo();
        BeanUtil.copyProperties(ownerInfoVO,ownerInfo);
        int id = ownerInfoMapper.insert(ownerInfo);
        if (id>0)
            return Result.success();
        return Result.failed("保存失败");
    }
}
