package com.xujie.pay.strategy.context.base;


import com.xujie.pay.api.dto.WxOrderDTO;
import com.xujie.pay.api.dto.WxOrderRequest;

public abstract class AbstractPayContext {
    abstract public WxOrderDTO processOrder(WxOrderRequest request);
}
