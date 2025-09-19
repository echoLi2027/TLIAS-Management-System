package com.zzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Talis16Application {

    public static void main(String[] args) {
        SpringApplication.run(Talis16Application.class, args);
    }

}
