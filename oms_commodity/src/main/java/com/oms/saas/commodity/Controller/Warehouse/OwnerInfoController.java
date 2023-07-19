package com.oms.saas.commodity.Controller.Warehouse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.commodity.Entity.Warehouse.OwnerInfo;
import com.oms.saas.commodity.Vo.Archives.GoodsCategoryVO;
import com.oms.saas.commodity.Vo.Warehouse.OwnerInfoVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.mapper.Warehouse.OwnerInfoMapper;
import com.oms.saas.commodity.service.Warehouse.OwnerInfoService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerInfoController {

    @Resource
    private OwnerInfoService ownerInfoService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @SneakyThrows
    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@Validated OwnerInfoVO ownerInfoVO){
        Console.log(ownerInfoVO.toString());
        if (ownerInfoService.save(ownerInfoVO))
            return Result.success();
        return Result.failed("保存失败");
    }

    @SneakyThrows
    @PostMapping(value = "/list")
    @ResponseBody
    public Result list(@RequestParam(required = false) String owner_code,@RequestParam(value = "page",defaultValue = "1") Integer page){
        Page<OwnerInfo> list = ownerInfoService.list(owner_code, page, pageSize);
        return Result.success(list);
    }
}
