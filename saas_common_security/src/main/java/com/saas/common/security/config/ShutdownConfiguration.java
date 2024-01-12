package com.saas.common.security.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ShutdownConfiguration {

    public ShutdownConfiguration() {
        System.out.println("==============ShutdownConfiguration-init================");
        // TODO Auto-generated constructor stub
    }

    @PreDestroy
    public void preDestroy() {
       try {
           System.out.println("==============关闭后台任务线程================");
           ExecutorService executor = Executors.newSingleThreadExecutor();
           executor.shutdown(); // 关闭线程池
           if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
               executor.shutdownNow(); // 强制关闭线程池
           }
           System.out.println("==============关闭其他工具================");
       }catch (Exception e){
           System.out.println("==============关闭异常================"+e.getMessage());
       }
    }

}
