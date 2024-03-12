package com.oms.saas.commodity.service.impl.Fegin;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.api.ResultCode;
import com.oms.saas.commodity.service.FeignClients.FeginGateWayService;
import org.springframework.stereotype.Service;

@Service
public class FeginGateWayFailbackServiceImpl implements FeginGateWayService {
    @Override
    public Result testHello() {
        return Result.failed(ResultCode.CURRENTLIMITING);
    }
}
