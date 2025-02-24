package com.xujie;


import com.xujie.strategy.wx.hupijiao.config.HuPiJiaoPayConfig;
import com.xujie.strategy.wx.wxpay.config.WxPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
