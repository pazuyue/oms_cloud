package com.oms.saas.commodity.service.impl.Fegin;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.api.ResultCode;
import com.oms.saas.commodity.service.FeignClients.Brand.FeginBrandService;
import org.springframework.stereotype.Service;

@Service
public class FeginFailbackServiceImpl implements FeginBrandService {
    @Override
    public Result getBrandByUserId(String token, Integer userId) {
        return Result.failed(ResultCode.CURRENTLIMITING);
    }

    @Override
    public Result checkToken(String token) {
        return Result.failed(ResultCode.CURRENTLIMITING);
    }
}
