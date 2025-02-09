package com.xujie.domain.service.impl;

import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.OrderHandlerContext;
import com.xujie.domain.service.OrderDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
    @Resource
    private OrderHandlerContext orderHandlerContext;
    @Override
    public Order processOrder(Order order) {
        orderHandlerContext.process(order);
        return order;
    }
}
