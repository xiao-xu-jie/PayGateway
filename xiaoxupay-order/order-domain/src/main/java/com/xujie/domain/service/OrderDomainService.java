package com.xujie.domain.service;

import com.xujie.domain.entity.Order;

import java.util.List;

public interface OrderDomainService {
    Order processOrder(Order order);

    void handleOrderExpired(String openNo);

    void handleOrderPaid(String openNo);

    void handleOrderPaid(List<String> list);

    Order queryOrder(String appid, String siteSecret, Long tradeNo);
}
