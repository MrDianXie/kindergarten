package com.dq408.kindergarten.utils.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    //该参数是否必须，如果true的话，当请求中没有传递该参数就报错。
    // 就是请求的url路径上如果需要参数的话是否必须传参(跟@RequestParam中的required是否需要参数一样)，
    // 例：如果是/hello/{id}则必须传个id值
    boolean required() default true;
}