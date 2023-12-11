package com.oms.saas.inventory.Controller;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.oms.saas.inventory.api.Result;
import com.oms.saas.inventory.dto.jwt.JwtInfo;
import com.oms.saas.inventory.until.DynamicRoutingDataSource;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Resource
    private JwtInfo jwtInfo;
    @Value("${databaseName:false}")
    private String databaseName;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/echo/{string}")
    public Result echo(@PathVariable String string) {
        return Result.success("Hello," + string);
    }

    @GetMapping("/testSwitchDataSource")
    public Result testSwitchDataSource(){
        String companyCode = jwtInfo.getCompanyCode().toLowerCase();
        System.out.println("companyCode:"+companyCode);
        databaseName = databaseName.toLowerCase();
        String dataKey =companyCode+"_"+databaseName;
        System.out.println("dataKey:"+dataKey);
        //需要注意的是手动切换的数据源，最好自己在合适的位置
        //调用DynamicDataSourceContextHolder.clear()清空当前线程的数据源信息。
        DynamicDataSourceContextHolder.clear();
        //切换到对应poolName的数据源
        DynamicDataSourceContextHolder.push(dataKey);
        return Result.success();
    }

}