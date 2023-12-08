package com.oms.saas.inventory.controller.wmsTicket;

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
@Controller
@RequestMapping("/wms-inventory")
public class WmsInventoryController {


    @Autowired
    private WmsInventoryService wmsInventoryService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<WmsInventory>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<WmsInventory> aPage = wmsInventoryService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WmsInventory> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(wmsInventoryService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody WmsInventory params) {
        wmsInventoryService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }


    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody WmsInventory params) {
        wmsInventoryService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
