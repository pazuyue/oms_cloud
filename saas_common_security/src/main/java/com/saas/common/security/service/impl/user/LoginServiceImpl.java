package com.saas.common.security.service.impl.user;

import com.saas.common.security.api.Result;
import com.saas.common.security.config.UserPro;
import com.saas.common.security.dto.UserDTO;
import com.saas.common.security.entity.User.SysUser;
import com.saas.common.security.entity.User.UserCompanys;
import com.saas.common.security.mapper.User.SysUserMapper;
import com.saas.common.security.service.user.LoginService;
import com.saas.common.security.until.JwtUtils;
import com.saas.common.security.until.RedisCache;
import com.saas.common.security.vo.user.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public Result login(SysUser user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserPro userPro = (UserPro)authenticate.getPrincipal();
        UserDTO userDTO = userPro.getUser();
        int user_id = userDTO.getId();
        String companyCode = userDTO.getCompanyCode();
        String nickName = userDTO.getNickName();
        Map map =new HashMap();
        map.put("user_id",user_id);
        map.put("company_code",companyCode);
        map.put("nick_name",nickName);
        System.out.println("createJwtMap"+map);
        String jwt = jwtUtils.createJwt(map);
        redisCache.setCacheObject("login:"+user_id,userDTO,3600, TimeUnit.SECONDS);
        return Result.success(jwt);
    }

    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser)authentication.getPrincipal();
        String redisKey = "login:"+sysUser.getId();
        redisCache.deleteObject(redisKey);
        return Result.success();
    }
}
