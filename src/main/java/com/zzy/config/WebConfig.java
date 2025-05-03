package com.zzy.config;

import com.zzy.interceptor.InterceptorDemo;
import com.zzy.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private InterceptorDemo interceptorDemo;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        register customized interceptor obj
//        set intercept path as all request will be intercepted.
//        /* means only intercept current layer's path
//        e.g. /* intercept /emps, but cannot intercept /emps/1
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
