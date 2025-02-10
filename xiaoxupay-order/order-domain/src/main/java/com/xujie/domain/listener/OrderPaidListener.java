package com.xujie.domain.listener;

import com.xujie.common.event.OrderPaidEvent;
import com.xujie.domain.service.OrderDomainService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPaidListener implements ApplicationListener<OrderPaidEvent> {
    @Resource
    private OrderDomainService orderDomainService;
    @Override
    public void onApplicationEvent(OrderPaidEvent event) {
        orderDomainService.handleOrderPaid(event.getOpenNo());
    }
}
