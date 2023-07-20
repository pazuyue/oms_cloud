package com.saas.common.security.controller.Brand;

import com.saas.common.security.api.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.saas.common.security.service.Brand.BrandInfoService;
import com.saas.common.security.entity.Brand.BrandInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 品牌信息表 前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-07-20
 */
@RestController
@RequestMapping("/brandInfo")
public class BrandInfoController {


    @Autowired
    private BrandInfoService brandInfoService;

    @GetMapping(value = "/")
    public Result<Page<BrandInfo>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<BrandInfo> aPage = brandInfoService.page(new Page<>(current, pageSize));
        return Result.success(aPage);
    }

    @GetMapping(value = "/{id}")
    public Result<BrandInfo> getById(@PathVariable("id") String id) {
        return Result.success(brandInfoService.getById(id));
    }

    @PostMapping(value = "/create")
    @SneakyThrows
    public Result<Object> create(@RequestBody BrandInfo params) {
        System.out.println(params);
        brandInfoService.save(params);
        return Result.success();
    }

    @PostMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable("id") String id) {
        brandInfoService.removeById(id);
        return Result.success();
    }

    @PostMapping(value = "/update")
    public Result<Object> update(@RequestBody BrandInfo params) {
        brandInfoService.updateById(params);
        return Result.success();
    }
}
