package com.zzy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class JwtUtils {

    static String keyString = "emhvdXppeWFuMTk5NzExMjY=hssdjkhewrduwebkakbjga";
    static SecretKey secretKey = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));

    public static String createToken(HashMap<String,Object> claims){

        String jwt = Jwts.builder()
                        .signWith(secretKey)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                        .compact();


        log.info("jwt is : {}", jwt);

        return jwt;
    }

    public static Claims parseToken(String jwt){

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        log.info("parsed token is : {}", claims);
        return claims;

    }
}
