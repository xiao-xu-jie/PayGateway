package com.xujie.pay.strategy.wx.hupijiao;

import com.xujie.pay.strategy.wx.hupijiao.config.HuPiJiaoPayConfig;
import com.xujie.pay.strategy.wx.hupijiao.impl.HuPiJiaoWxPayService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "pay.wx.channel", value = "hupijiao")
@EnableConfigurationProperties(value = HuPiJiaoPayConfig.class)
public class HuPiJiaoWxAutoConfiguration {

    @ConditionalOnBean(name = "huPiJiaoPayConfig")
    @Bean(name = "wxHuPiJiaoPayService")
    public HuPiJiaoWxPayService wxPayService() {
        return new HuPiJiaoWxPayService();
    }

}
