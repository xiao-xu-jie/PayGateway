package com.xujie.domain.service;

import com.xujie.domain.entity.Order;

public interface OrderDomainService {
    Order processOrder(Order order);
    void handleOrderExpired(String openNo);
    void handleOrderPaid(String openNo);
}
