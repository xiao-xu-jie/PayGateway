package com.xujie.pay.strategy.wx.wxpay;

import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.xujie.pay.strategy.wx.wxpay.core.IWxPayChannelService;
import com.xujie.pay.strategy.wx.wxpay.core.impl.WxPayChannelServiceImpl;
import com.xujie.pay.strategy.wx.wxpay.impl.WxPayService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xujie
 * @since 2025/2/24 22:05
 **/

@Configuration
@ConditionalOnProperty(prefix = "pay.wx.channel", name = "wxOfficial")
public class WxPayAutoConfiguration {


    @Bean
    @ConditionalOnBean(NativePayService.class)
    public WxPayService wxPayService() {
        return new WxPayService();
    }

    @Bean
    @ConditionalOnBean(WxPayService.class)
    public IWxPayChannelService wxPayChannelService() {
        return new WxPayChannelServiceImpl();
    }
}
