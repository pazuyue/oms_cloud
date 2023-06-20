package com.oms.saas.platform_management.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;
import com.oms.saas.platform_management.Mapper.Jdp.JdpTbTradeMapper;
import com.oms.saas.platform_management.service.PullOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TmPullOrderServiceImpl implements PullOrderService {

    @Autowired
    private JdpTbTradeMapper jdpTbTradeMapper;

    @Override
    public Page<JdpTbTrade> getOrdersByModified(Date sTime, Date eTime, Integer page, Integer pageSize) {
        QueryWrapper<JdpTbTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("modified",sTime)
                .le("modified",eTime);
        Page<JdpTbTrade> aPage = jdpTbTradeMapper.selectPage(new Page<>(page, pageSize),queryWrapper);
        return aPage;
    }
}
