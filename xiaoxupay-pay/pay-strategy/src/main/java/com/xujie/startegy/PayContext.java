package com.xujie.startegy;

import jakarta.annotation.Resource;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 支付策略上下文
 */
@RefreshScope
@Component
public class PayContext {
    @Resource
   private PayService wxPayService;

    public PayService getWxPayService(){
        return this.wxPayService;
    }


}
