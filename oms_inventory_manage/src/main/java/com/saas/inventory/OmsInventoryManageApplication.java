package com.saas.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
//@ServletComponentScan
@EnableFeignClients
@MapperScan({"com.oms.saas.commodity.mapper.*"})
@SpringBootApplication
public class OmsInventoryManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmsInventoryManageApplication.class, args);
    }

}
