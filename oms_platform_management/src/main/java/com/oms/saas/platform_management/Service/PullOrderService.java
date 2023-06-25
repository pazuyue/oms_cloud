package com.oms.saas.platform_management.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;

import java.text.ParseException;

public interface PullOrderService {
    public Page<JdpTbTrade> getOrdersByModified(String startTime, String endTime, Integer page, Integer pageSize) throws ParseException;
}
