import cn.hutool.core.lang.Console;
import com.oms.saas.platform_management.Entity.User;
import com.oms.saas.platform_management.Mapper.User.UserMapper;
import com.oms.saas.platform_management.PlatformManagementApplication;
import com.oms.saas.platform_management.api.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PlatformManagementApplication.class })
public class MybatisTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetUserId(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("token","eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMSIsImlhdCI6MTY4OTczMzI2NCwiZXhwIjoxNjg5NzM2ODY0fQ.LvYQiDpW30cFwcnpma5AuWinOUYak7J7bJQiXIcJGAw");
        HttpEntity<Result> httpEntity = new HttpEntity<>( headers);
        //return restTemplate.getForObject("http://commonSecurity/user/userInfo", Result.class,httpEntity);
        //3. 有请求头，没参数，result3.getBody()获取响应参数
        ResponseEntity<Result> result = restTemplate.exchange("http://commonSecurity/userManage/check", HttpMethod.GET, httpEntity, Result.class);
        Result body = result.getBody();
        Map<String, Object> map =(HashMap)body.getData();
        Console.log(map);

    }


}
