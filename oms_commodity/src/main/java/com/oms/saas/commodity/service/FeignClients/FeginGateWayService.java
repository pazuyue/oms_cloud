package com.oms.saas.commodity.service.FeignClients;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.service.impl.Fegin.FeginGateWayFailbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "CommonGateWay",fallback = FeginGateWayFailbackServiceImpl.class)
public interface FeginGateWayService {
    @GetMapping(value = "/security/test/hello")
    public Result testHello();
}
