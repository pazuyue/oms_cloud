package com.oms.saas.commodity.Filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.until.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter // 注册拦截器，并添加拦截路径‘/user/getOne’
public class JWTFilter implements Filter {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("===> TestFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===> chain.doFilter 后执行处理 response 的相关方法");
        // 在response header里设置一个token
        setToken(servletRequest,servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);// 处理请求和响应的分界线
    }

    private void setToken(ServletRequest servletRequest,ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String token = req.getHeader("token");
        System.out.println("token"+token);
        if (StrUtil.isBlank(token)){
            // 异常捕获，发送到expiredJwtException
            req.setAttribute("expiredJwtException", "token is empty");

            req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
        }

        System.out.println("===> chain.doFilter 后执行处理 setToken 的相关方法");
        HttpHeaders headers = new HttpHeaders();
        headers.add("token",token);
        HttpEntity<Result> httpEntity = new HttpEntity<>( headers);
        try {
            ResponseEntity<String> result = restTemplate.exchange("http://commonSecurity/userManage/check", HttpMethod.GET, httpEntity, String.class);
            System.out.println("===> chain.doFilter 后执行处理 body 的相关方法"+result);
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            JSONObject data = jsonObject.getJSONObject("data");
            String companyCode = data.getString("company_code");
            if (StrUtil.isBlank(companyCode))
                req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
            jwtInfo.setCompanyCode(companyCode);
            System.out.println("===> chain.doFilter 后执行处理 jwtInfo 的相关方法"+jwtInfo.toString());
        }catch (Exception exception){
            System.out.println("===> chain.doFilter 后执行处理 exception 的相关方法"+exception.getMessage());
            // 异常捕获，发送到expiredJwtException
            req.setAttribute("expiredJwtException", exception.getMessage());
            //将异常分发到/expiredJwtException控制器
            req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("===> TestFilter destroy");
        Filter.super.destroy();

    }
}
