package com.saas.common.security.config;

import com.saas.common.security.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPro extends User {

    private UserDTO userDTO;

    public UserPro(UserDTO userDTO, Collection<? extends GrantedAuthority> authorities) {
        super(userDTO.getUserName(), userDTO.getPassword(), authorities);
        this.userDTO = userDTO;
    }

    public UserDTO getUser() {
        return this.userDTO;
    }

}
