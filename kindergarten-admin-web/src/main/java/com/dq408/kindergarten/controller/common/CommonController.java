package com.dq408.kindergarten.controller.common;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.StateCode;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
     * @param response
     * @param username
     * @throws IOException
     */
    @PassToken
    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response,String username) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/useravatar/" +username + ".png");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(classPathResource.getInputStream(), response.getOutputStream());

    }


    /**
     *
     * @param token
     * @return Map
     */
    @PassToken
    @PostMapping("/whoAmI")
    public Map<String,Object> whoAmI(@RequestHeader("X-Admin-Token") String token){

        try {
            //解析token
            Long userId = JwtUtil.passToken(token);
            //判断token是否有效
            if (userId == 0) {//无效
                return AjaxResult.fail(StateCode.TOKEN_OVERDUE, "token无效");
            } else {//有效
                User user = userService.getOne(new QueryWrapper<User>()
                        .eq("uid", userId)
                );

                //通过用户id查询该用户
                if (user != null) {//用户存在
                    //构造用户信息返回
                    Map<String, Object> userInfo = new HashMap<>();

                    userInfo.put("username", user.getUsername());
                    userInfo.put("avatar", user.getAvatar());

                    return AjaxResult.success(userInfo);
                } else {
                    return AjaxResult.fail();
                }
            }
        } catch (JWTVerificationException e){
            return AjaxResult.fail(StateCode.TOKEN_EXPIRED,"操作失败");
        }

    }



}
