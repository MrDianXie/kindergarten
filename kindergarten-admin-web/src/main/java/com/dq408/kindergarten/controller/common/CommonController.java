package com.dq408.kindergarten.controller.common;


import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.StateCode;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.PassToken;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * CommonController
 * 用于处理通用事务
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/home")
public class CommonController {

    @Autowired
    private UserService userService;


    /**
     * 获取头像
     * @param response 用于响应图片
     * @param username 用户名
     * @throws IOException io异常
     */
    @PassToken
    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response,String username) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/useravatar/" +username + ".png");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(classPathResource.getInputStream(), response.getOutputStream());

    }


    /**
     * 验证用户信息
     * @param token 验证令牌
     * @return Map
     */
    @PassToken
    @GetMapping("/whoAmI")
    public Map<String,Object> whoAmI(@RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token){

        try {
            //解析token
            Long userId = JwtUtil.passToken(token);
            //判断token是否有效
            if (userId == 0) {
                //无效
                return AjaxResult.fail(StateCode.TOKEN_OVERDUE, "token无效");
            } else {//有效
                User user = userService.getOne(new QueryWrapper<User>()
                        .eq("uid", userId)
                );

                //通过用户id查询该用户
                if (user != null) {
                    //用户存在
                    //构造用户信息返回
                    return AjaxResult.success(user);
                } else {
                    return AjaxResult.fail();
                }
            }
        } catch (JWTVerificationException e){
            return AjaxResult.fail(StateCode.TOKEN_EXPIRED,"操作失败");
        }

    }


    /**
     * 验证用户输入密码是否正确
     * @param token token
     * @param uid 用户id
     * @param pass 用户密码
     * @return map
     */
    @UserLoginToken
    @PostMapping("/verifyPass")
    public Map<String, Object> verifyPass(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long uid,
            String pass
    ){
        String password = userService.getById(uid).getPassword();
        if (password.equals(DigestUtil.md5Hex(pass))){
            return AjaxResult.success();
        } else {
            return AjaxResult.fail();
        }
    }



}
