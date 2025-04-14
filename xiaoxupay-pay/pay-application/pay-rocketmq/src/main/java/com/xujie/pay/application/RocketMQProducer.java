package com.xujie.pay.application;

import cn.hutool.core.lang.Pair;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
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
    public void sendDelayMessage(String topic, String tag, String messageText, int delayLevel) {
        // 拼接 topic 和 tag
        String destination = topic + ":" + tag;

        // 设置延时级别
        Message<String> message = MessageBuilder.withPayload(messageText)
                .build();
        try {
            rocketMQTemplate.asyncSend(destination,
                    message
                    ,
                    new SendCallback() {
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            log.info("MQ 发送成功：{}", destination);
                        }

                        @Override
                        public void onException(Throwable throwable) {
                            log.error("MQ 发送异常：{}", destination, throwable);
                        }
                    },
                    3000L); // 延时级别
        } catch (Exception e) {
            log.error("RocketMq发送消息异常", e);
        }
    }
}