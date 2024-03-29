package com.oms.saas.commodity.service.FeignClients.Brand;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.impl.Fegin.FeginFailbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commonSecurity",fallback = FeginFailbackServiceImpl.class)
public interface FeginBrandService {

    @PostMapping(value = "/brandInfo/getBrandByUserId")
    public Result getBrandByUserId(@RequestHeader("token") String token, @RequestParam("userId") Integer userId);

    @GetMapping(value = "userManage/check")
    public Result checkToken(@RequestHeader("token") String token);
}
