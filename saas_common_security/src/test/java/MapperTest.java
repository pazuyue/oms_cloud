
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    @Test
    public void testSgin() {
        String api = "188688";
        String secret = "61dfd4115e68545620e77f2a2559438b";
        String timestamp = "1706700905";
        String nonce = "测试";
        String signature = this.generateSignature(api, timestamp, nonce, secret);
        System.out.println("SHA-1 Hash: " + signature);
    }


    public String generateSignature(String appKey, String timestamp, String nonce, String dataEnterAppSecret) {
        // 组合数组
        String[] elements = {appKey, timestamp, nonce, dataEnterAppSecret};

        // 按照SORT_STRING规则排序（这里假设是字典序）
        Arrays.sort(elements, Comparator.comparing(String::toString));

        // 拼接成一个字符串
        StringBuilder sortedString = new StringBuilder();
        for (String element : elements) {
            sortedString.append(element);
        }

        // MD5加密
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Digest.digest(sortedString.toString().getBytes("UTF-8"));
            StringBuilder md5Hex = new StringBuilder();
            for (byte b : md5Bytes) {
                md5Hex.append(String.format("%02x", b));
            }
            String md5Hashed = md5Hex.toString();

            // 添加timestamp的最后一个字符
            char lastTimestampChar = timestamp.charAt(timestamp.length() - 1);

            // 将MD5哈希值与timestamp的最后一个字符拼接
            String combinedStr = md5Hashed + lastTimestampChar;

            // SHA-1加密
            MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
            byte[] sha1Bytes = sha1Digest.digest(combinedStr.getBytes("UTF-8"));
            StringBuilder sha1Hex = new StringBuilder();
            for (byte b : sha1Bytes) {
                sha1Hex.append(String.format("%02x", b));
            }
            return sha1Hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to compute signature", e);
        }
    }
}
