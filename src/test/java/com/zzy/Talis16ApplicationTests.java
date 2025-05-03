package com.zzy;

import com.zzy.exception.GlobalExceptionHandler;
import com.zzy.exception.PropagateException;
import com.zzy.pojo.Result;
import com.zzy.pojo.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class Talis16ApplicationTests {

    @Test
    void contextLoads() {
        if (true){
            throw new PropagateException("test");
        }
    }

    @Autowired
    GlobalExceptionHandler handler;

    @Test
    void exceptionTest() {
        Result result = handler.propEx(new PropagateException(new Student()));
        System.out.println("Error message: " + result.getMsg());
        // 或者使用断言
        // assertEquals("对不起，该班级下有学生，不能直接删除。", result.getMsg());
    }

    @Test
    void testGenJwt(){
//        1. create secret key
        String secreteString = "emhvdXppeWFuMTk5NzExMjY=hssdjkhewrduwebkakbjga";
        SecretKey secretKey = Keys.hmacShaKeyFor(secreteString.getBytes(StandardCharsets.UTF_8));
//        javax.crypto.spec.SecretKeySpec@929612e8
        System.out.println(secretKey);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Sheldon");
//        2. generate JWT
        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .compact();

        System.out.println(jwt);
    }

    @Test
    void testParseJwt(){

//        1. get the secretKey as the generated secretKey
        String secreteString = "emhvdXppeWFuMTk5NzExMjY=hssdjkhewrduwebkakbjga";
        SecretKey secretKey = Keys.hmacShaKeyFor(secreteString.getBytes(StandardCharsets.UTF_8));

//        2. get jwt
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJTaGVsZG9uIiwiZXhwIjoxNzQ2ODg2MzgzfQ.3ttlrV94dHzesyplpKKZz5dlBBWDQ7z8_fbXBq9K_sY";

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        System.out.println(claims);
    }

}
