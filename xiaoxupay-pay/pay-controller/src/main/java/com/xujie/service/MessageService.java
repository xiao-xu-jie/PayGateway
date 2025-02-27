package com.xujie.service;

import cn.hutool.core.date.DateUtil;
import com.xujie.application.RocketMQProducer;
import com.xujie.application.redis.utils.RedisUtils;
import com.xujie.common.enums.AggregateType;
import com.xujie.common.enums.OutBoxMessageType;
import com.xujie.common.exception.CustomException;
import com.xujie.infra.entity.OutboxMessage;
import com.xujie.infra.service.OutboxMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MessageService {
    @Resource
    private RocketMQProducer rocketMQProducer;
    @Resource
    private OutboxMessageService outboxMessageService;
    private final ThreadPoolExecutor threadPoolExecutor;

    {
        threadPoolExecutor = new ThreadPoolExecutor(10
                , 20
                , 60 * 10
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(1000)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public void sendOrderPaidMessage(String openNo) {
        // 发送支付成功消息
        CompletableFuture.runAsync(() -> {
            rocketMQProducer.sendTaggedMessage("order", "paid", openNo);
            log.info("发送支付成功消息成功：{}", openNo);
        }, threadPoolExecutor);
        // 保存OutBoxMessage
        CompletableFuture.runAsync(() -> {
            saveOrderPaidOutBoxMessage(openNo);
        }, threadPoolExecutor);
        // 发送站点通知消息
        CompletableFuture.runAsync(() -> {
            this.sendNotifySiteMessage(openNo);
        }, threadPoolExecutor);
        CompletableFuture.runAsync(() -> {
            saveSiteNotifyOutBoxMessage(openNo);
        }, threadPoolExecutor);

    }

    private void saveSiteNotifyOutBoxMessage(String openNo) {
        Optional<Object> cacheObject = RedisUtils.getCacheObject("order:" + openNo);
        log.info("保存站点通知OutBoxMessage：{}", openNo);
        cacheObject.orElseThrow(() -> new CustomException("站点信息缓存错误！"));
        OutboxMessage paidOutboxMessage = OutboxMessage.builder()
                .aggregateId(cacheObject.get().toString())
                .aggregateType(AggregateType.SITE.getType())
                .payload(cacheObject.get().toString())
                .createdAt(DateUtil.date())
                .type(OutBoxMessageType.SITE_NOTIFY.getType())
                .build();
        outboxMessageService.save(paidOutboxMessage);
    }

    private void saveOrderPaidOutBoxMessage(String openNo) {
        log.info("保存订单支付成功OutBoxMessage：{}", openNo);
        OutboxMessage paidOutboxMessage = OutboxMessage.builder()
                .aggregateId(openNo)
                .aggregateType(AggregateType.ORDER.getType())
                .payload(openNo)
                .createdAt(DateUtil.date())
                .type(OutBoxMessageType.ORDER_PAID.getType())
                .build();
        outboxMessageService.save(paidOutboxMessage);
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
