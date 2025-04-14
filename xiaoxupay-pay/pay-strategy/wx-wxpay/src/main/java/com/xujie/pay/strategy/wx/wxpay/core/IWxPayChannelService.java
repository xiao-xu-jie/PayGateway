package com.xujie.pay.strategy.wx.wxpay.core;


import com.xujie.pay.api.dto.WxOrderDTO;
import com.xujie.pay.api.dto.WxOrderRequest;

public interface IWxPayChannelService {

    WxOrderDTO createPcOrder(WxOrderRequest wxOrderRequest);

    WxOrderDTO createAppOrder(WxOrderRequest wxOrderRequest);

    WxOrderDTO createH5Order(WxOrderRequest wxOrderRequest);

}
