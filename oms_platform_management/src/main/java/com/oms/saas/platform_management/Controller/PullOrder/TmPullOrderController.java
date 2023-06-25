package com.oms.saas.platform_management.Controller.PullOrder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oms.saas.platform_management.Entity.JdpTbTrade;
import com.oms.saas.platform_management.api.Result;
import com.oms.saas.platform_management.service.Impl.TmPullOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
    public Result<Page<JdpTbTrade>> pullOrder() throws ParseException {
        String startTime="2023-05-30 00:00:00";
        String endTime = "2023-05-31 00:00:00";
        int page =1;
        int pageSize=10;
        Result<Page<JdpTbTrade>> apage = Result.success(tmPullOrderService.getOrdersByModified(startTime,endTime,page,pageSize));
        System.out.println("Result:"+apage.getData());
        return apage;
    }
}
