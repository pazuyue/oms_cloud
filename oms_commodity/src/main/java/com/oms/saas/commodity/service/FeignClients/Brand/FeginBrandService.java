package com.oms.saas.commodity.service.FeignClients.Brand;

import com.oms.saas.commodity.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("commonSecurity")
public interface FeginBrandService {

    @PostMapping(value = "/brandInfo/getBrandByUserId")
    public Result getBrandByUserId(@RequestHeader("token") String token, @RequestParam("userId") Integer userId);
}
