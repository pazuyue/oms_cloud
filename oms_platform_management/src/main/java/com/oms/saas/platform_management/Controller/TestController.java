package com.oms.saas.platform_management.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.oms.saas.platform_management.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello," + string;
    }


    @GetMapping("/restTemplateTest")
    @ResponseBody
    public ResponseEntity restTemplateTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token","eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMSIsImlhdCI6MTY4ODg5NjgzNCwiZXhwIjoxNjg4OTAwNDM0fQ.60iHYELtzS4Fs11MBrqwinhkmDfiu7M8CSqV-knyR_Y");
        HttpEntity<Result> httpEntity = new HttpEntity<>( headers);
        //return restTemplate.getForObject("http://commonSecurity/user/userInfo", Result.class,httpEntity);
        //3. 有请求头，没参数，result3.getBody()获取响应参数
        ResponseEntity<Result> result = restTemplate.exchange("http://OmsCommodity/test/echo/hello", HttpMethod.GET, httpEntity, Result.class);
        return result;
    }
}