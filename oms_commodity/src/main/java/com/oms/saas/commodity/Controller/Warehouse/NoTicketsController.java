package com.oms.saas.commodity.Controller.Warehouse;

import com.oms.saas.commodity.Vo.Warehouse.NoTicketsVO;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.Warehouse.NoTicketsService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noTickets")
public class NoTicketsController {

    @Resource
    private NoTicketsService ticketsService;

    @PostMapping("/save")
    public Result save(@Validated NoTicketsVO vo){
        ticketsService.save(vo);
        return Result.failed("添加失败");
    }
}
