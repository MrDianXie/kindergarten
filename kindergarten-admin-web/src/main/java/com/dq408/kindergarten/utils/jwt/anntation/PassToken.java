package com.dq408.kindergarten.utils.jwt.anntation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})//可以给一个方法进行注解，可以给一个类进行注解
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface PassToken {
    //该参数是否必须，如果true的话，当请求中没有传递该参数就报错。
    boolean required() default true;
}
