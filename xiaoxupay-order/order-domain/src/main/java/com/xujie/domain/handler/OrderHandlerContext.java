package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;
import com.xujie.domain.handler.common.SiteInfoCheckHandler;
import com.xujie.domain.handler.concret.OrderCreateHandler;
import com.xujie.domain.handler.concret.ParamsCheckHandler;
import com.xujie.domain.strategy.ChannelContext;
import com.xujie.site.api.feign.SiteFeignApi;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 订单处理上下文
 */
@Component
public class OrderHandlerContext {

    private final AbstractOrderHandler handler;

    public OrderHandlerContext(ChannelContext channelContext, SiteFeignApi siteFeignApi, Environment environment) {
        handler = new ParamsCheckHandler(environment);
        handler.setNext(new SiteInfoCheckHandler(siteFeignApi))
                .setNext(new OrderCreateHandler(channelContext));
    }

    public void process(Order order) {
        handler.handle(order);
    }

}
