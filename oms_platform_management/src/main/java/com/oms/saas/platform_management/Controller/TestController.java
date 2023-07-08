package com.oms.saas.platform_management.Controller;

import com.oms.saas.platform_management.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
    public void restTemplateTest() {
        System.out.println(restTemplate.getForObject("http://OmsCommodity/test/echo/hello", String.class));
    }
}