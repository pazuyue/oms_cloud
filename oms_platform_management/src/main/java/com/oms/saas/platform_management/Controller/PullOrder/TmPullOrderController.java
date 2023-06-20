package com.oms.saas.platform_management.Controller.PullOrder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;
import com.oms.saas.platform_management.service.Impl.TmPullOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/pull")
@RefreshScope
public class TmPullOrderController {

    @Autowired
    private TmPullOrderServiceImpl tmPullOrderService;

    @RequestMapping(value = "/pullOrder", method = GET)
    @ResponseBody
    public Page<JdpTbTrade> pullOrder() {
        Page<JdpTbTrade> list = null;
        String startTime="2023-05-30 00:00:00";
        String endTime = "2023-05-31 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date sTime = simpleDateFormat.parse(startTime);
            Date eTime = simpleDateFormat.parse(endTime);
            System.out.println("dateString:"+sTime);
            System.out.println("dateString:"+eTime);
          int page =1;
          int pageSize=10;
            list = tmPullOrderService.getOrdersByModified(sTime,eTime,page,pageSize);
          return list;
      }catch (Exception exception){
          exception.printStackTrace();
      }
        return list;
    }
}
