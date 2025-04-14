package com.xujie.order.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class OrderExpiredEvent extends ApplicationEvent {
    private final String openNo;

    public OrderExpiredEvent(Object source, String openNo) {
        super(source);
        this.openNo = openNo;
    }
}
