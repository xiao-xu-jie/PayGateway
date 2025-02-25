package com.xujie.strategy.wx.wxpay.core;

import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.strategy.wx.wxpay.config.WxPayConfig;
import jakarta.annotation.Resource;

public interface IWxPayChannelService {

   WxOrderDTO createPcOrder(WxOrderRequest wxOrderRequest);
   WxOrderDTO createAppOrder(WxOrderRequest wxOrderRequest);
   WxOrderDTO createH5Order(WxOrderRequest wxOrderRequest);

}
