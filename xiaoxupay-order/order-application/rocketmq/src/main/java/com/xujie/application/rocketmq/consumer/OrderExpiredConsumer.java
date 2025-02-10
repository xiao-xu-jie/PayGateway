package com.xujie.application.rocketmq.consumer;

import com.xujie.application.rocketmq.producer.RocketMQProducer;
import com.xujie.application.rocketmq.publisher.OrderEventPublisher;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "order-expired", topic = "order", selectorExpression = "expire")
public class OrderExpiredConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Resource
    private RocketMQProducer rocketMQProducer;
    @Resource
    private OrderEventPublisher orderEventPublisher;

    @Override
    public void onMessage(String openNo) {
        log.info("[OrderPaidConsumer]订单超时消息：{}", openNo);
        orderEventPublisher.publishOrderExpiredEvent(openNo);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    }
}
