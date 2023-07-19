package com.oms.saas.commodity.Controller.Jwt;

import com.oms.saas.commodity.api.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtExceptionController {

    /**
     * 重新抛出异常
     */
    @PostMapping("/expiredJwtException")
    public Result expiredJwtException(HttpServletRequest request) {
        Object jwtException = request.getAttribute("expiredJwtException");
        return Result.failed(jwtException.toString());
    }

    @PostMapping("/signatureException")
    public Result signatureException(HttpServletRequest request){
        Object jwtException = request.getAttribute("signatureException");
        return Result.failed(jwtException.toString());
    }

}
