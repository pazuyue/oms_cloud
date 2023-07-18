package com.oms.saas.commodity.Filter;

import cn.hutool.core.util.StrUtil;
import com.oms.saas.commodity.Entity.User.SysUser;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.until.RedisCache;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class JWTFilter implements Filter {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String token = req.getHeader("token");
        if (StrUtil.isBlank(token)){
            Result result = restTemplate.getForObject("http://commonSecurity/userManage/userInfo", Result.class);
            return;
        }
        String redisKey = "login:"+token;
        SysUser sysUser = redisCache.getCacheObject(redisKey);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
