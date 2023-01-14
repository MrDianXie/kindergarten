package com.dq408.kindergarten.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class JwtUtil {
    public static  String getToken(String userCode,String userPassword){
        //创建token
        //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        //withAudience()存入需要保存在token的信息，这里把userCode存入token中     sign：签名
        String token = JWT.create().withAudience(userCode).sign(Algorithm.HMAC256(userPassword));
        return token;
    }
}