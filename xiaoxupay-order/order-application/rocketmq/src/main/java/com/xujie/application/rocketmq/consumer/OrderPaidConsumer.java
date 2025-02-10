package com.xujie.application.rocketmq.consumer;

import com.xujie.application.rocketmq.publisher.OrderEventPublisher;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "order",selectorExpression = "paid")
public class OrderPaidConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Resource
    private OrderEventPublisher orderEventPublisher;
    @Override
    public void onMessage(String openNo) {
        log.info("[OrderPaidConsumer]订单支付成功消息：{}",openNo);
        orderEventPublisher.publishOrderPaidEvent(openNo);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
    }
}
