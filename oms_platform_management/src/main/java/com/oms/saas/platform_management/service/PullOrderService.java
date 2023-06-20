package com.oms.saas.platform_management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;

import java.util.Date;
import java.util.List;

public interface PullOrderService {
    public Page<JdpTbTrade> getOrdersByModified(Date sTime, Date eTime, Integer page, Integer pageSize);
}
