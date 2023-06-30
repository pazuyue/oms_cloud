package com.saas.common.security.until;

import com.alibaba.fastjson2.JSON;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.vo.user.JwtUser;
import com.saas.common.security.vo.user.LoginUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "config.jwt")
public class JwtUtils {
    //@Value("{jwt.skin.key}")
    //key的大小必须大于或等于256bit,需要32位英文字符，一个英文字符为：8bit,一个中文字符为12bit
    private String secret;
    private Long expire;
    //设置加密算法
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //获取header中的数据
    private final Integer GET_HEADER_DATA = 0;
    //获取payload中的数据
    private final Integer GET_PAYLOAD_DATA = 1;

    /**
     * 获取转换后的私钥对象
     *
     * @return
     */
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 生成JWT
     *
     * @param user_id
     * @return
     */
    public String createJwt(String user_id) {
        Map map =new HashMap();
        map.put("user_id",user_id);
        return Jwts.builder()
                .setClaims(map)//设置携带参数
                .setIssuedAt(new Date(System.currentTimeMillis()))//创建时间
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000))//过期时间
                .signWith(getSecretKey(), signatureAlgorithm)//设置加密算法和私钥
                .compact();
    }

    /**
     * 解析JWS，返回一个布尔结果
     *
     * @param jwsString
     * @return
     */
    public Boolean parseJwt(String jwsString) {
        boolean result = false;
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())//设置私钥
                    .build()
                    .parseClaimsJws(jwsString);//要解析的jws
            result = true;
        } catch (JwtException e) {
            e.getMessage();
        }
        return result;
    }


    public String getJson(String jwsString, Integer code) {
        //判断解析结果如果失败返回空，如果有全局异常处理，此处可抛自定义异常进行处理
        if (!parseJwt(jwsString)) return null;
        //将jws中的数据编码串截取出来使用Base64解析成字节数组
        //byte[] decodePayLoad = Base64Utils.decodeFromString(jwsString.split("\\.")[code]);
        byte[] decodePayLoad = Base64.getDecoder().decode(jwsString.split("\\.")[code]);
        return new String(decodePayLoad);
    }

    public Map<String, Object> getData(String jwsString, Integer code) {
        //此处使用的阿里的fastJson,可使用其他的工具将字节json字节转map
        return JSON.parseObject(getJson(jwsString, code), Map.class);
    }

    /**
     * 获取header中的数据
     *
     * @param jwsString
     * @return
     */
    public Map<String, Object> getHeader(String jwsString) {
        return getData(jwsString, GET_HEADER_DATA);
    }

    /**
     * 获取PayLoad中携带的数据
     *
     * @param jwsString
     * @return
     */
    public Map<String, Object> getPayLoad(String jwsString) {
        return getData(jwsString, GET_PAYLOAD_DATA);
    }

    /**
     * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
     *
     * @param jwsString
     * @return
     */
    public Map<String, Object> getPayLoadALSOExcludeExpAndIat(String jwsString) {
        Map<String, Object> map = getData(jwsString, GET_PAYLOAD_DATA);
        map.remove("exp");
        map.remove("iat");
        return map;
    }
}



