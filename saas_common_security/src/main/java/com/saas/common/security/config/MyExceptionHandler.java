package com.saas.common.security.config;

import com.saas.common.security.api.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        System.out.println("全局异常捕获>>>:"+e);
        return Result.failed(e.getMessage());
    }
}
