package com.saas.common.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.oms.saas.platform_management.Mapper"})
public class CommonSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonSecurityApplication.class, args);
    }
}
