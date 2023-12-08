package com.oms.saas.inventory.Controller.wmsTicket;

import com.oms.saas.inventory.api.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.oms.saas.inventory.service.wmsTicket.WmsInventoryService;
import com.oms.saas.inventory.entity.wmsTicket.WmsInventory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 仓库库存表维度：sku_sn + store_code 前端控制器
 * </p>
 *
 * @author 月光光
 * @since 2023-12-08
 */
@RestController
@RequestMapping("/wms-inventory")
public class WmsInventoryController {


    @Autowired
    private WmsInventoryService wmsInventoryService;
    @Value("${pageable.page.size:10}")
    private Integer pageSize;

    @GetMapping(value = "/list")
    public Result<Page<WmsInventory>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = this.pageSize;
        }
        Page<WmsInventory> aPage = wmsInventoryService.page(new Page<>(current, pageSize));
        return Result.success(aPage);
    }

    @GetMapping(value = "/{id}")
    public Result<WmsInventory> getById(@PathVariable("id") String id) {
        return  Result.success(wmsInventoryService.getById(id));
    }

    @PostMapping(value = "/create")
    public Result<Object> create(WmsInventory params) {
        wmsInventoryService.save(params);
        return Result.success();
    }
}
