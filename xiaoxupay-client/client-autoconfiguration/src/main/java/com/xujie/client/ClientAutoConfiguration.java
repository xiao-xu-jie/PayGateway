package com.xujie.client;


import com.xujie.client.api.controller.ClientNotifyController;
import com.xujie.client.config.XPayConfig;
import com.xujie.client.core.util.RequestUtil;
import com.xujie.client.service.impl.XOrderServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "pay", name = "client.enable", havingValue = "true")
@EnableConfigurationProperties(XPayConfig.class)
@Import({RequestUtil.class, XOrderServiceImpl.class, ClientNotifyController.class})
public class ClientAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("======= XOrder 加载成功 =======");
    }
}
