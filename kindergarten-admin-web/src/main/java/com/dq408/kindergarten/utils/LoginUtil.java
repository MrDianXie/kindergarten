package com.dq408.kindergarten.utils;

import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.vo.LoginInfoVo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginUtil {

    /** admin角色id */
    private static final Integer ROLE_ID = 2;


    /**
     * 用户信息验证
     * @param user
     * @return
     */
    public static Map<String,Object> verifyUserInfo(User user,String code, LoginInfoVo loginInfoVo){

        /** 账号验证 */
        if ( user == null ){
            return AjaxResult.fail(StateCode.ACCOUNT_ERR,"用户信息错误",null);
        } else {

            /** 密码验证 */
            if (!loginInfoVo.getPassword().equals(user.getPassword())){
                return AjaxResult.fail(StateCode.PASSWORD_ERR,"用户信息错误");
            }

            /** 验证码验证 */
            System.out.println("code:"+code);
            System.out.println(loginInfoVo.getCode());
            if (!code.equals(loginInfoVo.getCode()) && !code.toUpperCase(Locale.ROOT).equals(loginInfoVo.getCode())){
                return AjaxResult.fail(StateCode.CAPTCHA_ERR,"验证码错误");
            }

            /** 检查角色 */
            if (user.getRoleid() != ROLE_ID){
                return  AjaxResult.fail(StateCode.ROLE_ERR,"用户信息错误");
            }

            /** 登录成功 */
            HashMap<String,Object> data = new HashMap<>();
            HashMap<String,Object> userInfo = new HashMap<>();
            userInfo.put("avatar",user.getAvatar());
            userInfo.put("username", user.getUsername());
            System.out.println(user.getUid().longValue());
            data.put("token", JwtUtil.getToken(user.getUid(),null));
            data.put("userInfo",userInfo);
            return AjaxResult.success(data);
        }

    }




}
