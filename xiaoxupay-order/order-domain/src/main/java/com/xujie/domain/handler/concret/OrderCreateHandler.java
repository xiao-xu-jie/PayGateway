package com.xujie.domain.handler.concret;

import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.AbstractOrderHandler;
import com.xujie.domain.strategy.ChannelContext;
import com.xujie.domain.strategy.ChannelStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCreateHandler extends AbstractOrderHandler {
    private final ChannelContext channelContext;

    /**
     * 通过订单策略上下文分发请求进行处理
     *
     * @param order
     */
    @Override
    public void doHandle(Order order) {
        ChannelStrategy channelStrategy = channelContext.distributeChannelStrategy(order.getChannel());
        channelStrategy.handle(order);
    }

    public OrderCreateHandler(ChannelContext channelContext) {
        this.channelContext = channelContext;
    }
}
