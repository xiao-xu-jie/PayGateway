package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;
import com.xujie.domain.strategy.ChannelContext;
import com.xujie.domain.strategy.ChannelStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCreateHandler extends OrderHandler {
    private final ChannelContext channelContext;
    @Override
    public void doHandle(Order order) {
        ChannelStrategy channelStrategy = channelContext.distributeChannelStrategy(order.getChannel());
        channelStrategy.handle(order);
    }
    public OrderCreateHandler(ChannelContext channelContext) {
        this.channelContext = channelContext;
    }
}
