package com.dq408.kindergarten.interceptor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.StateCode;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.PassToken;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 新增AuthenticationInterceptor拦截器，在controller访问需要进行token验证的路径则会进行拦截处理
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        //从http请求头中取出token
        String token = request.getHeader("X-Admin-Token");
        System.out.println("token:"+token);
        //如果不是映射到方法直接通过(不是映射方法指的是没有说明请求路径的方法，如controller中的普通方法)
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();//获取请求的映射方法
        //检查映射方法是否有passToken注解，有则跳过认证，判断该请求映射方法上是否有passToken注解
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查映射方法是否有@UserLoginToken注解，有则进行token认证对比
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()) {
                //执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }

                //获取token中的userCode
                Long userId = 0L;

                try {
                    userId = JwtUtil.passToken(token);
                } catch (JWTDecodeException e) {
                    throw new RuntimeException("token验证失败");
                }

                User user = userService.getOne(new QueryWrapper<User>().eq("uid",userId));
                if(user == null){
                    throw new RuntimeException("用户不存在，请重新登录!");
                }
                //验证token    verifier:校验机    Algorithm：算法
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token验证失败");
                }
                return true;
            }
        }
        return false;

    }
}