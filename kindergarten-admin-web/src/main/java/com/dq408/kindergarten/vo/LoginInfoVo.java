package com.dq408.kindergarten.vo;

import lombok.Data;

/**
 * 登录信息
 * @author XIE_HRZGZ
 * */
@Data
public class LoginInfoVo {

    private String username;
    private String password;
    private Boolean autoLogin;
    private String code;


}
