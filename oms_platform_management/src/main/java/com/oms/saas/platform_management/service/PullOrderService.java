package com.oms.saas.platform_management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;
import com.oms.saas.platform_management.api.Result;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface PullOrderService {
    public Page<JdpTbTrade> getOrdersByModified(String startTime, String endTime, Integer page, Integer pageSize) throws ParseException;
}
