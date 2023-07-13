package com.oms.saas.commodity.Controller.Goods;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.commodity.Entity.Goods.GoodsSkuSnInfoTmp;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Goods.GoodsService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;
    /**
     * 商品导入
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping(value = "/export")
    @ResponseBody
    public Result export(@RequestParam(value = "file", required = true) MultipartFile file,@RequestParam(value = "import_batch") String import_batch) {
        ImportParams params = new ImportParams();
        List<GoodsVO> goodsList = ExcelImportUtil.importExcel(
                file.getInputStream(),
                GoodsVO.class, params);
        boolean b;
        if (ObjectUtil.isEmpty(import_batch)){
            b= goodsService.export(goodsList);
        }else {
            b = goodsService.export(goodsList,import_batch);
        }

        if (b){
            return Result.success();
        }
        return Result.failed("导入失败");
    }

    @SneakyThrows
    @PostMapping(value = "/list")
    @ResponseBody
    public Result exportList(@RequestParam(value = "import_batch") String importBatch,@RequestParam(value = "page",defaultValue = "1") Integer page)
    {
        Page<GoodsSkuSnInfoTmp> list = goodsService.exportList(importBatch, page, pageSize);
        return Result.success(list);
    }
}
