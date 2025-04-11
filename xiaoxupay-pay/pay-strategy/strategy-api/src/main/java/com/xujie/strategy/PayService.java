package com.xujie.strategy;

import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;

import java.util.Map;


public interface PayService {
    int order = Integer.MAX_VALUE;

    WxOrderDTO createOrder(WxOrderRequest orderRequest);

    Object checkNotify(Map<String, Object> map);

    default int getOrder() {
        return order;
    }
}
