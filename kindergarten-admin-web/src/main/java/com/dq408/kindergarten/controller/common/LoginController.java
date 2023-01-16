package com.dq408.kindergarten.controller.common;
import cn.hutool.captcha.LineCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.LoginUtil;
import com.dq408.kindergarten.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dq408.kindergarten.utils.jwt.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    /** 验证码 */
    private static String code;



    @GetMapping ("/verifyCode")
    public void getVerCode(HttpServletResponse response) throws IOException {
        //生成一个验证码的图片，设置宽高，验证码随机的位数
        LineCaptcha verCode = new LineCaptcha(120,40,4,50);
        //获取验证码
        code = verCode.getCode();
        System.out.println(code);
        //将验证码图片响应回浏览器
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        OutputStream out = response.getOutputStream();
        verCode.write(out);
        out.flush();
    }


    /**
     * 验证登录
     * @param loginInfoVo
     * @return
     */
    @PassToken
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginInfoVo loginInfoVo){
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username",loginInfoVo.getUsername())
                .eq("mnemonic_code",loginInfoVo.getUsername())
        );

        //判断账号密码是否正确
        return LoginUtil.verifyUserInfo(user,code,loginInfoVo);
    }


}
