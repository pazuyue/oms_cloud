package com.oms.saas.platform_management.Filter;

import com.oms.saas.platform_management.api.Result;
import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class JWTFilter implements Filter {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        restTemplate.getForObject("http://commonSecurity/test/hello", Result.class);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
