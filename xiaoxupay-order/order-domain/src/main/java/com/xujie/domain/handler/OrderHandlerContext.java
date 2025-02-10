package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.common.SiteInfoCheckHandler;
import com.xujie.domain.strategy.ChannelContext;
import com.xujie.feign.SiteFeignClient;
import org.springframework.stereotype.Component;

@Component
public class OrderHandlerContext {

    private final OrderHandler handler;

    public OrderHandlerContext(ChannelContext channelContext, SiteFeignClient siteFeignClient) {
        handler = new SiteInfoCheckHandler(siteFeignClient);
        handler.setNext(new OrderCreateHandler(channelContext));
    }
    public void process(Order order) {
        handler.handle(order);
    }

}
