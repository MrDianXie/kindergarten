package com.dq408.kindergarten.utils;

import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.TokenPassword;
import com.dq408.kindergarten.vo.LoginInfoVo;

import java.util.HashMap;
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
            return AjaxResult.fail(StateCode.ACCOUNT_ERR,"用户信息错误");
        } else {

            /** 密码验证 */
            if (!loginInfoVo.getPassword().equals(user.getPassword())){
                return AjaxResult.fail(StateCode.PASSWORD_ERR,"用户信息错误");
            }

            /** 验证码验证 */
            if (!code.equals(loginInfoVo.getCode())){
                return AjaxResult.fail(StateCode.CAPTCHA_ERR,"验证码错误");
            }

            /** 检查角色 */
            if (user.getRoleid() != ROLE_ID){
                return  AjaxResult.fail(StateCode.ROLE_ERR,"用户信息错误");
            }


            HashMap<String,Object> data = new HashMap<>();
            data.put("token", JwtUtil.getToken(user.getUsername(), TokenPassword.USER_TOKEN));
            data.put("avatar",user.getAvatar());
            data.put("username",user.getUsername());
            return AjaxResult.success(data);
        }

    }



}
