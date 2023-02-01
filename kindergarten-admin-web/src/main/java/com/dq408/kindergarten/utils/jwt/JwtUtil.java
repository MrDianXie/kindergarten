package com.dq408.kindergarten.utils.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.*;


public class JwtUtil {


    public static final String HEADER_TOKEN_NAME = "X-Admin-Token";
    // 秘钥
    static final String SECRET = "Kinder-Token";
    // 签名是有谁生成
    static final String ISSUSER = "DQ-B-408";
    // 签名的主题
    static final String SUBJECT = "this is kinder token";
    // 签名的观众
    static final String AUDIENCE = "ADMINWEBAPP";

    static final List<String> badTokenList = new ArrayList<>();


    /**
     * 生成token
     * @param userId 用户ID
     * @return String
     */
    public static String getToken(Long userId,Date nowDate){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            // 过期时间：1小时
            Date expireDate = getAfterDate(nowDate,0,0,0,50,0,0);
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("userId", userId)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    // 生成签名的时间
                    .withIssuedAt(nowDate)
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 解析token
     * @param token token
     * @return Long
     */
    public static Long passToken(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("userId");
            return claim.asLong();
        } catch (JWTVerificationException exception){
            System.out.println("JWT异常");
//            return 0L;
            throw exception;
        }
    }

    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }


    /**
     * 当有效时间小于等于10分钟自动续签
     * @param oldToken 旧token
     * @return Token
     */
    public static Map<String,Object> renewalToken (String oldToken){
        HashMap<String,Object> data = new HashMap<>();
        //获取当前token的过期时间
        long expiresTime = getExpiresAt(oldToken).getTime();
        //获取当前的系统时间
        long nowTime = System.currentTimeMillis();
        //将毫秒值化为秒
        long residualTime = (expiresTime - nowTime)/1000;

        System.out.println("当前token剩余："+residualTime+"秒钟过期");
        if (residualTime <= 600 && residualTime > 0){//token在十分钟内会过期
            //解析当前token中的userid
            Long userid = passToken(oldToken);
            badTokenList.add(oldToken);
            //创建新的token
//            System.out.println("token刷新了");
            System.out.println(badTokenList);
            String token = getToken(userid, new Date());
            data.put("token",token);
            return data;

        }

        //大于10分钟 返回原来的token
        data.put("token",oldToken);
        return data;
    }


    /**
     * 获取token过期时间
     * @param oldToken 客户端传入的token
     * @return Data
     */
    private static Date getExpiresAt(String oldToken){
        DecodedJWT jwt = verifyToken(oldToken);
        return jwt.getExpiresAt();

    }


    /**
     * 验证token是否过期
     * @param token token
     * @return DecodedJWT
     */
    public static DecodedJWT verifyToken(String token){
        DecodedJWT jwt;
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            jwt = verifier.verify(token);
        } catch (TokenExpiredException e){
            throw e;
        }

        return jwt;

    }



}

