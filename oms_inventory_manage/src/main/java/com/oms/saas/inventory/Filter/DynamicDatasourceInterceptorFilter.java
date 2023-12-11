package com.oms.saas.inventory.Filter;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.oms.saas.inventory.dto.jwt.JwtInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ShardingKey;

@Order(10)
@Component // 注册拦截器
public class DynamicDatasourceInterceptorFilter implements Filter {

    @Resource
    private JwtInfo jwtInfo;
    @Value("${databaseName:false}")
    private String databaseName;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("===> DynamicDatasourceInterceptor doFilter");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String companyCode = jwtInfo.getCompanyCode().toLowerCase();
        databaseName = databaseName.toLowerCase();
        String dataKey =companyCode+"_"+databaseName;
        System.out.println("dataKey:"+dataKey);
        DynamicDataSourceContextHolder.clear();
        //切换到对应poolName的数据源
        DynamicDataSourceContextHolder.push(dataKey);
        chain.doFilter(req,resp);
    }
}
