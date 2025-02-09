package com.xujie.domain.handler;

import com.xujie.domain.entity.Order;

public abstract class OrderHandler {
    private OrderHandler next;

    public void handle(Order order){
        doHandle(order);
        handleNext(order);
    }

    protected abstract void doHandle(Order order);
    public OrderHandler setNext(OrderHandler orderHandler) {
        next = orderHandler;
        return next;
    }
    public void handleNext(Order order) {
        if(next!=null) {
            next.handle(order);
        }
    }

}
