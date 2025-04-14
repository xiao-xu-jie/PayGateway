package com.xujie.order.domain.handler;

import com.xujie.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractOrderHandler implements Ordered {
    private AbstractOrderHandler next;


    public void handle(Order order) {
        doHandle(order);
    }

    protected abstract void doHandle(Order order);


}
