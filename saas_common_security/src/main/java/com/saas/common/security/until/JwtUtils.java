package com.saas.common.security.until;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    //密钥
    private static String sign ="cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=";
    //生成token
    public String generateToken(Authentication authentication) {
        //用户的核心标识
        String username = authentication.getName();
        // 过期时间 - 30分钟
        Date expireDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(generalKeyByDecoders())  //设置token加密方式和密
                .compact();
        return token;
    }

    public static SecretKey generalKeyByDecoders() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sign));
    }

    /**
     * 解密token
     * @param token
     * @return
     */
    public String getUsernameFromJWT(String token) {
        JwtParserBuilder builder = Jwts.parserBuilder();
        Jws<Claims> claimsJws = builder
                .setSigningKey(generalKeyByDecoders())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        log.error("验证 token  {}", token);
        try {
            JwtParserBuilder builder = Jwts.parserBuilder();

            Jws<Claims> claimsJws = builder
                    .setSigningKey(generalKeyByDecoders())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();
            // 检查token
            throw new BadCredentialsException("TOKEN已过期，请重新登录！");
        } catch (AuthenticationException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        } catch (Exception ex) {
            log.error("token认证失败 {}", ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}


