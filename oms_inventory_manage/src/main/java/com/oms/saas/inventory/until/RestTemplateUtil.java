package com.oms.saas.inventory.until;

import cn.hutool.core.lang.Console;
import com.oms.saas.inventory.api.Result;
import com.oms.saas.inventory.dto.jwt.JwtInfo;
import jakarta.annotation.Resource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private JwtInfo jwtInfo;

    public Result exchangePost(String url, HttpMethod httpMethod, MultiValueMap map) {
        Console.log("RestTemplateUtil");
        String token = jwtInfo.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        ParameterizedTypeReference<Result> responseBodyType = new ParameterizedTypeReference<>(){};
        Console.log(responseBodyType.toString());
        Result body = restTemplate.exchange(url, httpMethod, httpEntity, responseBodyType).getBody();
        Console.log(body.toString());
        return body;
    }
}
