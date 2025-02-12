package com.xujie.startegy.wx.hupijiao.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.xujie.application.RocketMQProducer;
import com.xujie.application.redis.utils.RedisUtils;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;
import com.xujie.common.exception.CustomException;
import com.xujie.common.utils.HashUtil;
import com.xujie.startegy.wx.hupijiao.AbstractHuPiJiaoPayService;
import com.xujie.startegy.wx.hupijiao.config.HuPiJiaoPayConfig;
import com.xujie.startegy.wx.hupijiao.constants.HuPiJiaoPayConstant;
import com.xujie.startegy.wx.hupijiao.entity.OrderRequest;
import com.xujie.startegy.wx.hupijiao.entity.RefundRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HuPiJiaoWxPayService extends AbstractHuPiJiaoPayService {
    @Resource(name = "webClient")
    private WebClient webClient;
    @Resource(name = "huPiJiaoPayConfig")
    private HuPiJiaoPayConfig config;
    @Resource
    private RocketMQProducer rocketMQProducer;

    private final Integer timeout = 5000;

    @Override
    public WxOrderDTO createOrder(WxOrderRequest orderRequest) {
        OrderRequest request = OrderRequest.builder()
                .orderId(orderRequest.getOpenNo())
                .title(orderRequest.getTitle())
                .totalFee(orderRequest.getTotalFee())
                .remark(orderRequest.getRemark())
                .build();
        Map<String, Object> requestBody = request.getReqBody();
        requestBody.put("appid", config.getAppid());
        requestBody.put("version", "1.1");
        requestBody.put("nonce_str", getNonceStr());
        requestBody.put(HuPiJiaoPayConstant.NOTIFY_URL, config.getNotifyUrl());
        requestBody.put(HuPiJiaoPayConstant.RETURN_URL, config.getReturnUrl());
        requestBody.put("time", getSecondTimestamp(new Date()));
        String hash = getHash(requestBody, config.getAppSecret());
        requestBody.put("hash", hash);
        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 创建订单请求体：{}", requestBody);
        }
        String post = null;
        try {
            post = post(config.getUrl(), requestBody, webClient, timeout);
        } catch (Exception e) {
            log.error("对接平台创建订单请求异常: ", e);
            throw new CustomException("对接平台创建订单请求异常！");
        }

        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送创建订单请求响应结果：{}", post);
        }
        JSONObject order0 = getJsonObject(post);
        WxOrderDTO build = WxOrderDTO.builder()
                .channel("wx")
                .openNo(orderRequest.getOpenNo())
                .jsonData(order0)
                .expireTime(DateUtil.offsetMinute(new Date(), 15))
                .transactionId(order0.getStr("openid"))
                .url(order0.getStr("url"))
                .urlQrcode(order0.getStr("url_qrcode"))
                .build();
        // 订单信息缓存，方便通知获取站点appid
        RedisUtils.setCacheObject("order:" + orderRequest.getOpenNo(), orderRequest.getSiteAppid(), 15, TimeUnit.MINUTES);
        rocketMQProducer.sendDelayMessage("order", "expire", orderRequest.getOpenNo(), 14);
        return build;
    }

    @Override
    public JSONObject refundOrder(RefundRequest refundRequest) {
        Map<String, Object> requestBody = refundRequest.getRefundMap();
        requestBody.put("appid", config.getAppid());
        requestBody.put("nonce_str", getNonceStr());
        requestBody.put("time", getSecondTimestamp(new Date()));
        String hash = getHash(requestBody, config.getAppSecret());
        requestBody.put("hash", hash);
        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送请求退款请求体：{}", requestBody);
        }
        String post = null;
        try {
            post = post(config.getRefundUrl(), requestBody, webClient, timeout);
        } catch (Exception e) {
            log.error("对接平台退款请求异常: ", e);
            throw new CustomException("对接平台退款请求异常！");
        }

        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送订单退款请求响应结果：{}", post);
        }
        return getJsonObject(post);
    }

    @Override
    public String checkNotify(Map<String, Object> map) {
        Assert.notEmpty(map, "参数不为空");
        log.info("[HuPijiao] 开始--支付成功回调：{}", map);
        String hash = HashUtil.hash(map.entrySet(), config.getAppSecret());
        String resHash = map.get("hash").toString();
        if (StringUtils.compare(resHash, hash) != 0) {
            log.error("[HuPijiao]支付回调异常：计算hash {}，返回hash：{}", hash, resHash);
            return "error";
        }
        String orderNo = map.get("trade_order_id").toString();
        Assert.notNull(orderNo, "回调订单号不为空！");
        log.info("[HuPijiao] 结束--支付成功回调：{}", orderNo);
        return orderNo;
    }


}
