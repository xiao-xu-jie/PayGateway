package com.xujie.strategy.wx.wxpay.core;

import cn.hutool.json.JSONObject;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;

import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;
import com.xujie.strategy.wx.wxpay.config.WxPayConfig;
import jakarta.annotation.Resource;

/**
 * @author Xujie
 * @since 2025/2/25 14:25
 * 微信通道抽象类
 **/


public abstract class AbstractWxPayChannel implements IWxPayChannelService{
    @Resource
    private WxPayConfig wxPayConfig;
    protected PrepayRequest buildPreRequest(WxOrderRequest wxOrderRequest) {
        PrepayRequest prepayRequest = new PrepayRequest();
        // 设置订单金额 传入单位是 元 转换成分
        Amount amount = new Amount();
        amount.setTotal((int) (wxOrderRequest.getTotalFee()*100));
        prepayRequest.setAmount(amount);

        prepayRequest.setDescription(wxOrderRequest.getTitle());
        prepayRequest.setAppid(wxPayConfig.getAppId());
        prepayRequest.setMchid(wxPayConfig.getMerchantId());
        prepayRequest.setNotifyUrl(wxPayConfig.getNotifyUrl()); // 设置公网回调url
        prepayRequest.setOutTradeNo(wxOrderRequest.getOpenNo());
        prepayRequest.setAttach(wxOrderRequest.getOpenNo());
        return prepayRequest;
    }
    protected WxOrderDTO buildWxOrderResponse(PrepayResponse prepayResponse, WxOrderRequest wxOrderRequest) {
        return WxOrderDTO.builder()
                .channel("wx")
                .transactionId(prepayResponse.getCodeUrl())
                .openNo(wxOrderRequest.getOpenNo())
                .jsonData(new JSONObject(prepayResponse))
                .urlQrcode(prepayResponse.getCodeUrl())
                .build();
    }
}
