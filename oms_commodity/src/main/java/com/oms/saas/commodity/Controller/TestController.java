package com.oms.saas.commodity.Controller;

import com.oms.saas.commodity.api.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/echo/{string}")
    public Result echo(@PathVariable String string) {
        return Result.success("Hello," + string);
    }
}