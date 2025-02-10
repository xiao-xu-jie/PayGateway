package com.xujie.service;

import com.xujie.application.RocketMQProducer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {
    @Resource
    private RocketMQProducer rocketMQProducer;
    public void sendOrderPaidMessage(String orderNo) {
        rocketMQProducer.sendTaggedMessage("order","paid",orderNo);
    }
}
