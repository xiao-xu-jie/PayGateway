package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractOrderHandler implements Comparable<Integer> {
    // 处理器优先级
    @Getter
    protected Integer priority = 0;
    private AbstractOrderHandler next;

    public void handle(Order order) {
        doHandle(order);
        handleNext(order);
    }

    protected abstract void doHandle(Order order);

    public AbstractOrderHandler setNext(AbstractOrderHandler orderHandler) {
        next = orderHandler;
        return next;
    }

    public void handleNext(Order order) {
        if (next != null) {
            next.handle(order);
        }
    }

    @Override
    public int compareTo(Integer o) {
        return this.priority - o;
    }
}
