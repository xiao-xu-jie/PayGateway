package com.xujie.domain.strategy;

import com.xujie.domain.entity.Order;

public abstract class ChannelStrategy {
    public abstract void handle(Order order);
}
