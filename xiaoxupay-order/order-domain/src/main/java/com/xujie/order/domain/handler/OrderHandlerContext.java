package com.xujie.order.domain.handler;

import com.xujie.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单处理上下文
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandlerContext {
    private final List<AbstractOrderHandler> handlers;

    public void process(Order order) {
        for (AbstractOrderHandler handler : handlers) {
            handler.handle(order);
        }
    }

}
