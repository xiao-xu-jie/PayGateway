package com.xujie.startegy.wx.hupijiao.impl;

import cn.hutool.json.JSONObject;
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

@Slf4j
public class HuPiJiaoWxPayService extends AbstractHuPiJiaoPayService {
    @Resource(name = "webClient")
    private WebClient webClient;
    @Resource(name = "huPiJiaoPayConfig")
    private HuPiJiaoPayConfig config;

    private final Integer timeout = 5000;

    @Override
    public JSONObject createOrder(OrderRequest orderRequest) {
        Map<String, Object> requestBody = orderRequest.getReqBody();
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
        return getJsonObject(post);
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
        Assert.notEmpty(map,"参数不为空");
        log.info("[HuPijiao] 开始--支付成功回调：{}", map);
        String hash = HashUtil.hash(map.entrySet(), config.getAppSecret());
        String resHash = map.get("hash").toString();
        if (StringUtils.compare(resHash, hash) != 0) {
            log.error("[HuPijiao]支付回调异常：计算hash {}，返回hash：{}", hash, resHash);
            return "error";
        }
        String orderNo = map.get("trade_order_id").toString();
        Assert.notNull(orderNo,"回调订单号不为空！");
        log.info("[HuPijiao] 结束--支付成功回调：{}", orderNo);
        return orderNo;
    }


}
