package com.oms.saas.commodity;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.oms.saas.commodity.mapper.*"})
public class OmsCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmsCommodityApplication.class, args);
    }
}
