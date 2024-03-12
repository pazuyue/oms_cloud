package com.saas.common.security.controller;

import com.saas.common.security.api.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${useLocalCache}")
    private String useLocalCache;

    @GetMapping("/hello")
    public Result hello() {
        String msg = "Hello,yueguang"+useLocalCache;
        return Result.success(msg);
    }
}
