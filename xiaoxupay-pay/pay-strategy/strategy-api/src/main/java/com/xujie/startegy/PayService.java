package com.xujie.startegy;

import com.xujie.common.annotations.Order;
import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;

import java.util.Map;


public interface PayService {
    WxOrderDTO createOrder(WxOrderRequest orderRequest);

    Object checkNotify(Map<String, Object> map);

    default  int getOrder() {
        Class<? extends PayService> aClass = this.getClass();
        if(aClass.isAnnotationPresent(Order.class)) {
            Order order = aClass.getAnnotation(Order.class);
            return order.value();
        }
        return Integer.MAX_VALUE;
    }
}
