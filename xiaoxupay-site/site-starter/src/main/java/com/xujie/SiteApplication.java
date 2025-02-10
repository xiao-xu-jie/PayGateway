package com.xujie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xujie.*.mapper")
@SpringBootApplication
public class SiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class,args);
    }
}
