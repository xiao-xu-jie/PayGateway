package com.xujie.order.domain.strategy;

import com.xujie.order.domain.entity.Order;

public abstract class ChannelStrategy {
    public abstract void handle(Order order);
}
