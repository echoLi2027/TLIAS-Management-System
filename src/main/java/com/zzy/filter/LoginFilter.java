package com.zzy.filter;

import com.zzy.pojo.Result;
import com.zzy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        1. if it's login, discharged
        String url = request.getRequestURL().toString();
        if (url.contains("/login")){
            log.info("login request, discharged.");
            filterChain.doFilter(request,response);
            return;
        }

//        1.5 get token
        String token = request.getHeader("token");
//        2. if token is empty
        if (token==null || token.isEmpty()){
            log.info("jwt is empty");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

//        3. if token is not empty
//            3.1. parse JWT
        try{
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("jwt is invalid");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("jwt is valid");
        filterChain.doFilter(request,response);
    }
}
