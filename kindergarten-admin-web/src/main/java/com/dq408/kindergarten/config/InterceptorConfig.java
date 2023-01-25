package com.dq408.kindergarten.config;

import com.dq408.kindergarten.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器,controller层中url访问先跑这个方法，然后到自定义拦截器里，然后再到controller具体方法
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截规则     addInterceptor():需要传入一个拦截器，可以是自定义拦截规则
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authenticationInterceptor());
        //addPathPatterns("/**")所有的请求都拦截
        InterceptorRegistration register = interceptorRegistration.addPathPatterns("/admin/**");

        //上一步拦截所有然后得到的也是一个InterceptorRegistration，调用excludePathPatterns方法指定可以放行的路径
        register.excludePathPatterns("/admin/auth/login");
        register.excludePathPatterns("/admin/auth/verifyCode");
        register.excludePathPatterns("/admin/home/getAvatar");
    }
    
    @Bean//AuthenticationInterceptor自定义的认证拦截器，自动配置并注入spring管理
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

}
