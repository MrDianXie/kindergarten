package com.dq408.kindergarten.controller.common;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 登录验证Controller
 * @author XIE_HRZGZ
 * */

@RestController
@RequestMapping("/admin/auth")
public class LoginController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginInfoVo loginInfoVo){
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username",loginInfoVo.getUsername())
                .eq("mnemonic_code",loginInfoVo.getUsername())
        );

        //判断账号密码是否正确
        if (user != null && user.getPassword().equals(loginInfoVo.getPassword())){
            return AjaxResult.success();
        } else {
            return AjaxResult.fail();
        }
    }


}
