package com.example.common_gate_way;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommonGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonGateWayApplication.class, args);
    }

}
