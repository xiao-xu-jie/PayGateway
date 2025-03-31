package com.xujie.strategy.wx.wxpay.core;


import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;

public interface IWxPayChannelService {

   WxOrderDTO createPcOrder(WxOrderRequest wxOrderRequest);
   WxOrderDTO createAppOrder(WxOrderRequest wxOrderRequest);
   WxOrderDTO createH5Order(WxOrderRequest wxOrderRequest);

}
