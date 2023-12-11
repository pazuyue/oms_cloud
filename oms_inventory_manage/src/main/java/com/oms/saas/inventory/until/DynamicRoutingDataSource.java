package com.oms.saas.inventory.until;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DynamicRoutingDataSource {

    @Value("${databaseName:false}")
    private String databaseName;
    /**
     * 手动切换的数据源
     * @param companyCode
     */
    public void switchDataSource(String companyCode) {
        databaseName = databaseName.toLowerCase();
        System.out.println("databaseName:"+databaseName);
        String dataKey = companyCode+"_"+databaseName;
        System.out.println(dataKey);
        if (!StrUtil.isBlank(databaseName)){
            //需要注意的是手动切换的数据源，最好自己在合适的位置
            //调用DynamicDataSourceContextHolder.clear()清空当前线程的数据源信息。
            DynamicDataSourceContextHolder.clear();
            //切换到对应poolName的数据源
            DynamicDataSourceContextHolder.push(companyCode);
        }
    }

}
