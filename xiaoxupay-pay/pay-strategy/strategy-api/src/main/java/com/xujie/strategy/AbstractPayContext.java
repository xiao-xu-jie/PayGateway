package com.xujie.strategy;


import com.xujie.api.dto.WxOrderDTO;
import com.xujie.api.dto.WxOrderRequest;

public abstract class AbstractPayContext {
    abstract public WxOrderDTO processOrder(WxOrderRequest request);
}
