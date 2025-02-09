package com.xujie.startegy.wx.hupijiao.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
@Configuration
@ConditionalOnProperty(prefix = "pay.wx.hupijiao",
        name = {"Appid", "AppSecret","notifyUrl","url","refundUrl"})
@ConfigurationProperties(prefix = "pay.wx.hupijiao")
public class HuPiJiaoPayConfig {
    private String url;
    private String refundUrl;
    private String notifyUrl;
    private String returnUrl;
    private String Appid;
    private String AppSecret;
}
