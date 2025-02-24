package com.xujie.strategy.wx.wxpay.impl;

import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.startegy.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Xujie
 * @since 2025/2/24 22:18
 **/

@Slf4j
public class WxPayService implements PayService {
    @Resource
    private NativePayService nativePayService;
    @Override
    public WxOrderDTO createOrder(WxOrderRequest orderRequest) {
        return null;
    }

    @Override
    public Object checkNotify(Map<String, Object> map) {
        return null;
    }
}
