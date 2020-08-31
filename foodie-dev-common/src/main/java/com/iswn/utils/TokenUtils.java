package com.iswn.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaofei.yan
 * @Create 2020-08-31 16:00
 * @Descript 使用token验证用户是否登录
 */
public class TokenUtils {
    /**
     * 设置过期时间
     */
    private static final long EXPIRE_DATE = 30 * 60 * 100000;

    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "YXFwnandyanyunning233";

    /**
     * 获取 Token
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static String getToken(String username, String password) {
        String token = "";

        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            // 秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HMAC256");
            // 携带 username, password 信息，生成签名
            token = JWT.create().withHeader(header).withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return token;
    }

    /**
     * 验证 token，通过返回true
     * @param token 需要校验的串
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String username = "xiaofei.yan";
        String password = "123";
        String token = getToken(username, password);
        System.out.println(token);

        // 校验 token
        boolean b = verify(token);
        System.out.println(b);
    }
}
