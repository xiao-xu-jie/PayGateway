package com.xujie.order.domain.handler.concret;

import com.xujie.order.common.enums.ChannelTypeEnum;
import com.xujie.order.domain.entity.Order;
import com.xujie.order.domain.handler.AbstractOrderHandler;
import com.xujie.order.domain.strategy.ChannelContext;
import com.xujie.order.domain.strategy.ChannelStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateHandler extends AbstractOrderHandler {
    private final ChannelContext channelContext;

    /**
     * 通过订单策略上下文分发请求进行处理
     *
     * @param order
     */
    @Override
    public void doHandle(Order order) {
        String channel = order.getChannel();
        ChannelStrategy channelStrategy = channelContext.distributeChannelStrategy(Objects.requireNonNull(ChannelTypeEnum.getValue(channel)));
        channelStrategy.handle(order);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE - 1;
    }
}
