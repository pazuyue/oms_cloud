package com.saas.inventory.Filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.saas.inventory.api.Result;
import com.saas.inventory.dto.jwt.JwtInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Order(1)
@Component // 注册拦截器
public class JWTFilter implements Filter {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private JwtInfo jwtInfo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("===> JWTFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===> JWTFilter.doFilter 后执行处理 response 的相关方法");
        // 在response header里设置一个token
        if (checkToken(servletRequest,servletResponse))
            filterChain.doFilter(servletRequest, servletResponse);// 处理请求和响应的分界线
    }

    private boolean checkToken(ServletRequest servletRequest,ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String token = req.getHeader("token");
        System.out.println("token"+token);
        if (StrUtil.isBlank(token)){
            // 异常捕获，发送到expiredJwtException
            req.setAttribute("expiredJwtException", "token is empty");
            req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("token",token);
        HttpEntity<Result> httpEntity = new HttpEntity<>( headers);
        try {
            ResponseEntity<String> result = restTemplate.exchange("http://commonSecurity/userManage/check", HttpMethod.GET, httpEntity, String.class);
            System.out.println("===> chain.doFilter 后执行处理 body 的相关方法"+result);
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            JSONObject data = jsonObject.getJSONObject("data");
            String companyCode = data.getString("company_code");
            Integer userId = data.getInteger("user_id");
            String nickName = data.getString("nick_name");
            if (StrUtil.isBlank(companyCode))
                req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
            jwtInfo.setCompanyCode(companyCode);
            jwtInfo.setUserId(userId);
            jwtInfo.setToken(token);
            jwtInfo.setNickName(nickName);
            System.out.println("===> chain.doFilter 后执行处理 jwtInfo 的相关方法"+jwtInfo.toString());
            return true;
        }catch (Exception exception){
            System.out.println("===> chain.doFilter 后执行处理 exception 的相关方法"+exception.getMessage());
            // 异常捕获，发送到expiredJwtException
            req.setAttribute("expiredJwtException", exception.getMessage());
            //将异常分发到/expiredJwtException控制器
            req.getRequestDispatcher("/expiredJwtException").forward(req, resp);
            return false;
        }
    }

    @Override
    public void destroy() {
        System.out.println("===> TestFilter destroy");
        Filter.super.destroy();

    }
}
