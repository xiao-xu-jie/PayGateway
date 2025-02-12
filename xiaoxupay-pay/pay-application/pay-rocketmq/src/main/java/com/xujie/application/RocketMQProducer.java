package com.xujie.application;

import cn.hutool.core.lang.Pair;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RocketMQProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送简单消息
     */
    public void sendSimpleMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
        System.out.println("Message sent: " + message);
    }

    /**
     * 发送带 Tag 的消息
     */
    public void sendTaggedMessage(String topic, String tag, String message) {
        String destination = topic + ":" + tag; // 格式为 "topic:tag"
        rocketMQTemplate.convertAndSend(destination, message);
        System.out.println("Tagged message sent: " + message);
    }

    /**
     * 发送带对象的消息
     */
    public void sendObjectMessage(String topic, Pair myMessageObject) {
        rocketMQTemplate.convertAndSend(topic, myMessageObject);
        System.out.println("Object message sent: " + myMessageObject);
    }

    /**
     * 发送事务消息
     */
    public void sendTransactionalMessage(String topic, String message) {
        rocketMQTemplate.sendMessageInTransaction(topic, MessageBuilder.withPayload(message).build(), null);

    }

    /**
     * 发送延时消息
     */
    public void sendDelayMessage(String topic, String tag, String message, int delayLevel) {
        // 拼接 topic 和 tag
        String destination = topic + ":" + tag;

        // 设置延时级别
        SendResult sendResult = rocketMQTemplate.syncSend(destination,
                MessageBuilder.withPayload(message).build(),
                3000, // 超时时间（毫秒）
                delayLevel); // 延时级别
    }
}