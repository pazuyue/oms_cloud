package com.oms.saas.platform_management.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 开启负载均衡 Ribbon, 发送的请求都会被Ribbon拦截。必须使用应用名代替ip，否则报错
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}