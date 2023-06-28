package com.saas.common.security.vo.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;


@ToString
public class JwtUser implements Serializable {

    private String uid;

    private String username;

    private String nickName;

    private String phone;

    private String password;

    private Integer state;

    private String sessionKey;

    private Collection authorities;

    public JwtUser() {
    }

    public JwtUser(String uid, String username, String nickName, String phone, String password, Integer state, String sessionKey, Collection authorities) {
        this.uid = uid;
        this.username = username;
        this.nickName = nickName;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.sessionKey = sessionKey;
        this.authorities = authorities;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }


    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return state == 1 || state == 3 || state == 4 || state == 5;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Collection getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection authorities) {
        this.authorities = authorities;
    }
}

