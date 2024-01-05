package com.saas.common.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"com.saas.common.security.Mapper"})
public class CommonSecurityApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CommonSecurityApplication.class);
        app.setRegisterShutdownHook(true); // 自动注册关闭钩子
        SpringApplication.run(CommonSecurityApplication.class, args);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // 执行优雅停机逻辑
                System.out.println("执行优雅停机逻辑");
                executor.shutdown(); // 关闭线程池
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow(); // 强制关闭线程池
                }
            } catch (InterruptedException e) {
                executor.shutdownNow(); // 强制关闭线程池
            }
        }));

    }
}
