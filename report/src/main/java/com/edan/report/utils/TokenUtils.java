package com.edan.report.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
 

public class TokenUtils {
    //设置过期时间，毫秒级，一天
    private static final long EXPIRE_DATE=1*24*60*60*1000;
    //token秘钥
    private static final String TOKEN_SECRET = "zhuangyujin";
 
    public static String getToken(String userId,String password){
 
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息,头部用于描述关于该JWT的最基本信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带userId，password信息，生成签名

            //获取创建者
            token = JWT.create()
                    //头部信息
                    .withHeader(header)
                    //载荷
                    .withClaim("userid",userId)
                    .withClaim("password",password)
                    //过期时间
                    .withExpiresAt(date)
                    //签名
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }
    //验证token
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    //获取token中信息 userId
    public static String getUserId(String token){
        try {
            String userId = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaim("userid").asString();
            return userId;
        } catch (TokenExpiredException e){
            e.printStackTrace();
            return null;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }



    

}
