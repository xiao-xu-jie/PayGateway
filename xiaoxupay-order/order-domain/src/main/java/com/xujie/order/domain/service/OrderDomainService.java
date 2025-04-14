package com.xujie.order.domain.service;

import com.xujie.order.domain.entity.Order;

import java.util.List;

public interface OrderDomainService {
    Order processOrder(Order order);

    void handleOrderExpired(String openNo);

    void handleOrderPaid(String openNo);

    void handleOrderPaid(List<String> list);

    Order queryOrder(String appid, String siteSecret, Long tradeNo);
}
