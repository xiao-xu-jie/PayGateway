package com.xujie.client.core.events.publisher;

import com.xujie.client.core.events.XOrderPaidEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XOrderEventPublisher {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishOrderPaidEvent(String openNo) {
        log.info("XOrderEventPublisher 发布 XOrderPaidEvent事件：{}", openNo);
        applicationEventPublisher.publishEvent(new XOrderPaidEvent(this, openNo));
    }


}
