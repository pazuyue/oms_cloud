package com.oms.saas.inventory.Controller.jwt;

import com.oms.saas.inventory.api.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtExceptionController {

    /**
     * 重新抛出异常
     */
    @RequestMapping("/expiredJwtException")
    public Result expiredJwtException(HttpServletRequest request) {
        Object jwtException = request.getAttribute("expiredJwtException");
        return Result.failed(jwtException.toString());
    }

    @RequestMapping("/signatureException")
    public Result signatureException(HttpServletRequest request){
        Object jwtException = request.getAttribute("signatureException");
        return Result.failed(jwtException.toString());
    }

}
