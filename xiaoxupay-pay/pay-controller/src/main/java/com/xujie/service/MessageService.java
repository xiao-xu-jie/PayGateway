package com.xujie.service;

import com.xujie.application.RocketMQProducer;
import com.xujie.application.redis.utils.RedisUtils;
import com.xujie.common.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MessageService {
    @Resource
    private RocketMQProducer rocketMQProducer;


    public void sendOrderPaidMessage(String openNo) {
        rocketMQProducer.sendTaggedMessage("order", "paid", openNo);
        // 发送站点通知消息
        this.sendNotifySiteMessage(openNo);
    }

    public void sendNotifySiteMessage(String openNo) {
        Optional<Object> cacheObject = RedisUtils.getCacheObject("order:" + openNo);
        cacheObject.orElseThrow(() -> new CustomException("订单已经过期，无需通知"));
        RedisUtils.deleteObject("order:" + openNo);
        StringBuilder sb = new StringBuilder();
        sb.append(openNo).append("-").append(cacheObject.get());
        rocketMQProducer.sendSimpleMessage("notify", sb.toString());
        log.info("发送站点通知消息成功：{}", sb);
    }

}
