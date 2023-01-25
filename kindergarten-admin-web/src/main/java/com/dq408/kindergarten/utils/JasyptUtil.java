package com.dq408.kindergarten.utils;

import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Locale;

public class JasyptUtil {
    public static void main(String[] args) {
        //创建加密对象
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        //设计加密方式
        encryptor.setPassword("xjhsdaw123@#$");

        //加密账号
        String user = encryptor.encrypt("root");
        String pass = encryptor.encrypt("root");

        System.out.println(user);
        System.out.println(pass);




    }

}
