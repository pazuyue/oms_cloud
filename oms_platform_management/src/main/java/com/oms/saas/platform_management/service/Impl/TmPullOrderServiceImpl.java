package com.oms.saas.platform_management.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;
import com.oms.saas.platform_management.Mapper.Jdp.JdpTbTradeMapper;
import com.oms.saas.platform_management.api.Result;
import com.oms.saas.platform_management.service.PullOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TmPullOrderServiceImpl implements PullOrderService {

    @Autowired
    private JdpTbTradeMapper jdpTbTradeMapper;

    @Override
    public Page<JdpTbTrade> getOrdersByModified(String startTime, String endTime, Integer page, Integer pageSize) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sTime = simpleDateFormat.parse(startTime);
        Date eTime = simpleDateFormat.parse(endTime);
        System.out.println("dateString:"+sTime);
        System.out.println("dateString:"+eTime);
        return this.getOrderByPage(sTime,eTime,page,pageSize);
    }


    private Page<JdpTbTrade> getOrderByPage(Date sTime, Date eTime, Integer page, Integer pageSize)
    {
        QueryWrapper<JdpTbTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("modified",sTime)
                .le("modified",eTime);
        return jdpTbTradeMapper.selectPage(new Page<>(page, pageSize),queryWrapper);
    }
}
