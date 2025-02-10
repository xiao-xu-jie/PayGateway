package com.xujie.application.rocketmq.publisher;

import com.xujie.common.event.OrderExpiredEvent;
import com.xujie.common.event.OrderPaidEvent;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    public void publishOrderPaidEvent(String openNo) {
        applicationEventPublisher.publishEvent(new OrderPaidEvent(this,openNo));
    }

    public void publishOrderExpiredEvent(String openNo) {
        applicationEventPublisher.publishEvent(new OrderExpiredEvent(this,openNo));
    }
}
