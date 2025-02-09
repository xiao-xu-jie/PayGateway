package com.xujie.startegy.wx.hupijiao;

import com.xujie.startegy.wx.hupijiao.impl.HuPiJiaoWxPayService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "pay.wx.channel",value = "hupijiao")
public class HuPiJiaoWxAutoConfiguration{

    @ConditionalOnBean(name = "huPiJiaoPayConfig")
    @Bean(name = "wxPayService")
    public HuPiJiaoWxPayService wxPayService(){
        return new HuPiJiaoWxPayService();
    }

}
