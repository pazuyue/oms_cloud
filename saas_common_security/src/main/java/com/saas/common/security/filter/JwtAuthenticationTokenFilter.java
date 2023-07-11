package com.saas.common.security.filter;

import com.alibaba.fastjson2.JSON;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }

        Map<String, Object> map = jwtUtils.getPayLoadALSOExcludeExpAndIat(token);
        String userId =map.get("user_id").toString();
        String redisKey = "login:"+userId;
        SysUser sysUser = redisCache.getCacheObject(redisKey);
        System.out.println("sysUser:"+sysUser);
        if (ObjectUtils.isEmpty(sysUser)){
            throw new RuntimeException("用户未登录");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
