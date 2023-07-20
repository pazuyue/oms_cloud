package com.saas.common.security.controller.Brand;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saas.common.security.api.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.saas.common.security.service.Brand.BrandInfoService;
import com.saas.common.security.entity.Brand.BrandInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @GetMapping(value = "/")
    public Result<Page<BrandInfo>> list(@RequestParam(required = false) Integer page) {
        if (page == null)
            page = 1;
        Page<BrandInfo> aPage = brandInfoService.page(new Page<>(page, pageSize));
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
    public Result<BrandInfo> update(@RequestBody BrandInfo params) {
        brandInfoService.updateById(params);
        return Result.success();
    }

    @PostMapping(value = "/getBrandByUserId")
    public Result<List<BrandInfo>> getBrandByUserId(@RequestParam(value = "userId") Integer userId){
        System.out.println("user_id:"+userId);
        List<BrandInfo> brandList = brandInfoService.getBrandByUserId(userId);
        return Result.success(brandList);
    }
}
