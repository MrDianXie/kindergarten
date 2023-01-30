package com.dq408.kindergarten.vo;

import lombok.Data;

/**
 * 登录信息
 * @author XIE_HRZGZ
 * */
public class LoginInfoVo {

    private String username;
    private String password;
    private Boolean autoLogin;
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(Boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
