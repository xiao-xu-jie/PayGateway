package com.xujie.domain.listener;

import com.xujie.common.event.OrderExpiredEvent;
import com.xujie.domain.service.OrderDomainService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderExpiredListener implements ApplicationListener<OrderExpiredEvent> {
    @Resource
    private OrderDomainService orderDomainService;

    @Override
    public void onApplicationEvent(OrderExpiredEvent event) {
        orderDomainService.handleOrderExpired(event.getOpenNo());
    }
}
