import com.oms.saas.platform_management.Entity.User;
import com.oms.saas.platform_management.Mapper.User.UserMapper;
import com.oms.saas.platform_management.PlatformManagementApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PlatformManagementApplication.class })
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addUser(){
        User user=new User();
        user.setName("朝雾轻晓");
        user.setAge(15);
        userMapper.insert(user);

    }


}
