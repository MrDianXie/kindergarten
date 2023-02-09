package com.dq408.kindergarten.utils;
/**
 * 响应状态码类
 * @author XIE_HRZGZ
 * */
public interface StateCode {

    /**
     * 请求成功
     */
    Integer SUCCEED = 408;


    /**
     * 账号错误
     */
    Integer ACCOUNT_ERR = 101;
    /**
     * 密码错误
     */
    Integer PASSWORD_ERR = 102;
    /**
     * 验证码错误
     */
    Integer CAPTCHA_ERR = 103;
    /**
     * 角色有误
     */
    Integer ROLE_ERR = 301;


    /**
     * 失败
     */
    Integer FAIL = 201;
    /**
     * token为null
     */
    Integer TOKEN_ISNULL = 208;
    /**
     * 该token查询不到对应用户
     */
    Integer TOKEN_USER_IS_NULL = 209;
    /**
     * token验证失败
     */
    Integer TOKEN_OVERDUE = 210;
    /**
     * token过期
     */
    Integer TOKEN_EXPIRED = 211;


}
