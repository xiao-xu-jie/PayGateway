package com.xujie.strategy;

import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;

public abstract class AbstractPayContext {
    abstract public WxOrderDTO processOrder(WxOrderRequest request);
}
