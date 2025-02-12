package com.xujie.application.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "notify", consumerGroup = "default")
public class SIteNotifyConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String info) {
        // 执行通知站点
        log.info("接收到站点通知信息：{}", info);
    }
}
