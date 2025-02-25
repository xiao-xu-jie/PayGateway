package com.xujie.strategy.wx.wxpay.core.impl;

import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.strategy.wx.wxpay.core.AbstractWxPayChannel;
import com.xujie.strategy.wx.wxpay.impl.WxPayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * @author Xujie
 * @since 2025/2/25 14:18
 * 微信订单创建实现类
 **/
@Slf4j
public class WxPayChannelServiceImpl extends AbstractWxPayChannel {
    @Resource
    private NativePayService nativePayService;
    @Override
    public WxOrderDTO createPcOrder(WxOrderRequest wxOrderRequest) {
        PrepayResponse prepay = nativePayService.prepay(buildPreRequest(wxOrderRequest));
        return buildWxOrderResponse(prepay,wxOrderRequest);
    }



    @Override
    public WxOrderDTO createAppOrder(WxOrderRequest wxOrderRequest) {
        return null;
    }

    @Override
    public WxOrderDTO createH5Order(WxOrderRequest wxOrderRequest) {
        return null;
    }
}
