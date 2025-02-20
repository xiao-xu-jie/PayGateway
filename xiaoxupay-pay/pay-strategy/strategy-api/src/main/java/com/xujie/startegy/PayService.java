package com.xujie.startegy;

import com.xujie.common.dto.WxOrderDTO;
import com.xujie.common.dto.WxOrderRequest;

import java.util.Map;


public interface PayService {
    WxOrderDTO createOrder(WxOrderRequest orderRequest);

    Object checkNotify(Map<String, Object> map);

}
