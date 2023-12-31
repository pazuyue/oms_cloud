package com.oms.saas.platform_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.oms.saas.platform_management.Mapper"})
public class PlatformManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformManagementApplication.class, args);
    }
}
