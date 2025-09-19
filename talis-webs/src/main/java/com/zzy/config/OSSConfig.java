package com.zzy.config;

import com.zzy.pojo.AliyunOSSProperties;
import com.zzy.utils.AliyunOSSOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {

    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties ossProperties){
        return new AliyunOSSOperator(ossProperties);
    }
}
