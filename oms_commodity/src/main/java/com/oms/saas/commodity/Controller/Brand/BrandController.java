package com.oms.saas.commodity.Controller.Brand;

import com.oms.saas.commodity.api.Result;
import com.oms.saas.commodity.dto.JwtInfo;
import com.oms.saas.commodity.service.FeignClients.Brand.FeginBrandService;
import com.oms.saas.commodity.until.RestTemplateUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private RestTemplateUtil restTemplateUtil;
    @Resource
    private JwtInfo jwtInfo;

    @Resource
    private FeginBrandService feginBrandService;

    @SneakyThrows
    @PostMapping(value = "/getBrandInfoByUserId")
    public Result getBrandInfoByUserId()
    {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("userId", jwtInfo.getUserId());

        return restTemplateUtil.exchangePost("http://commonSecurity/brandInfo/getBrandByUserId", HttpMethod.POST, paramMap);
    }

    @SneakyThrows
    @PostMapping(value = "/getBrandByUserId")
    public Result getBrandByUserId(){
        return feginBrandService.getBrandByUserId(jwtInfo.getToken(),jwtInfo.getUserId());
    }


}
