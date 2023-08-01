package com.oms.saas.commodity.Controller.Warehouse;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.lang.Console;
import com.oms.saas.commodity.Vo.Export.GoodsVO;
import com.oms.saas.commodity.Vo.Export.NoTicketsGoodsTmpVO;
import com.oms.saas.commodity.Vo.Warehouse.NoTicketsVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.NoTicketsGoodsTmpService;
import com.oms.saas.commodity.service.Warehouse.NoTicketsService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/noTickets")
public class NoTicketsController {

    @Resource
    private NoTicketsService ticketsService;
    @Resource
    private NoTicketsGoodsTmpService noTicketsGoodsTmpService;

    @PostMapping("/save")
    public Result save(@Validated NoTicketsVO vo){
       if (ticketsService.save(vo))
           return Result.success();
        return Result.failed("添加失败");
    }

    /**
     * 商品导入
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping(value = "/export")
    @ResponseBody
    public Result export(@RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(value = "no_sn") String noSn) {
        ImportParams params = new ImportParams();
        List<NoTicketsGoodsTmpVO> noTicketsGoodsTmpVOList = ExcelImportUtil.importExcel(
                file.getInputStream(),
                NoTicketsGoodsTmpVO.class, params);
        if (noTicketsGoodsTmpService.save(noTicketsGoodsTmpVOList,noSn))
            return Result.success();
        return Result.failed("导入失败");
    }
}
