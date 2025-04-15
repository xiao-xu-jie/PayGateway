package com.xujie.client.service.api;

import com.xujie.client.core.domain.XOrder;
import com.xujie.client.dto.XOrderDto;

public interface XOrderService {

    XOrder createOrder(XOrderDto.XOrderCreateRequest request);

    XOrder queryOrder(XOrderDto.XOrderQueryRequest request);
}
