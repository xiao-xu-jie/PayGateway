package com.xujie.strategy.wx.wxpay.impl;

import com.xujie.common.annotations.Order;

import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;
import com.xujie.strategy.PayService;
import com.xujie.strategy.wx.wxpay.core.impl.WxPayChannelServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.xujie.strategy.wx.wxpay.constants.WxPayConstant.WxPayChannel.PC;

/**
 * @author Xujie
 * @since 2025/2/24 22:18
 **/
@Order(1)
@Slf4j
public class WxPayService implements PayService {
    @Resource
    private WxPayChannelServiceImpl wxPayChannelService;

    @Override
    public WxOrderDTO createOrder(WxOrderRequest orderRequest) {
        String client = orderRequest.getClient();

        switch (client) {
            case PC:
                return wxPayChannelService.createPcOrder(orderRequest);
            default:
                log.info("支付通道待实现：{}", orderRequest);
                return null;
        }
    }

    @Override
    public Object checkNotify(Map<String, Object> map) {
        return null;
    }
}
