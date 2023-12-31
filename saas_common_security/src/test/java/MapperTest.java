
import com.saas.common.security.CommonSecurityApplication;
import com.saas.common.security.dto.UserDTO;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.until.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommonSecurityApplication.class})
public class MapperTest {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private JwtUtils jwtUtils;

    @Test
    public void addUserTest() {
        SysUser user = new SysUser();
        user.setUserName("yueguang");
        user.setNickName("朝雾轻晓");
        user.setPassword("22222");
        sysUserMapper.insert(user);

    }

    @Test
    public void bCryptPasswordEncoderTest() {
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(bCryptPasswordEncoder.matches("123456", encode));
    }

    @Test
    public void creatJWTTest() {
        Map userMap =new HashMap();
        userMap.put("user_id",1);
        String jwt = jwtUtils.createJwt(userMap);
        System.out.println(jwt);
        Map<String, Object> map = jwtUtils.getPayLoadALSOExcludeExpAndIat(jwt);
        System.out.println(map.get("user_id"));
    }

    @Test
    public void testLeftJoin(){
       try {
           UserDTO user = sysUserMapper.selectUserInfoWtihBrand(1);
           System.out.println("user"+user.toString());
       }catch (Throwable throwable){
           System.out.println(throwable.getMessage());
       }
    }
}
