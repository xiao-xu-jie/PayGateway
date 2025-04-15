package com.xujie.client.core.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class XOrderPaidEvent extends ApplicationEvent {
    private final String openNo;

    public XOrderPaidEvent(Object source, String openNo) {
        super(source);
        this.openNo = openNo;
    }
}
